package com.proinnovate.csvimportservice.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.proinnovate.csvimportservice.model.Project;
import com.proinnovate.csvimportservice.repository.ProjectRepository;

@Service
public class ProjectDataLoaderService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectDataLoaderService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        try {
            if (projectRepository.count() == 0) {
                loadDataFromCSV();
            } else {
                logger.info("La base de datos ya contiene datos. Omitiendo la carga del CSV.");
            }
        } catch (Exception e) {
            logger.error("Error durante la inicialización: ", e);
        }
    }

    @Transactional
    protected void loadDataFromCSV() throws Exception {
        ClassPathResource resource = new ClassPathResource("data/projects.csv");
        
        try (Reader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            
            HeaderColumnNameMappingStrategy<Project> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Project.class);

            CsvToBean<Project> csvToBean = new CsvToBeanBuilder<Project>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .withQuoteChar('"')
                    .build();

            List<Project> projects = csvToBean.parse();
            
            int batchSize = 100;
            int totalProjects = projects.size();
            
            for (int i = 0; i < totalProjects; i += batchSize) {
                int endIndex = Math.min(i + batchSize, totalProjects);
                List<Project> batch = projects.subList(i, endIndex);
                try {
                    projectRepository.saveAll(batch);
                    logger.info("Procesado lote {} de {} proyectos", i/batchSize + 1, 
                              (totalProjects + batchSize - 1)/batchSize);
                } catch (Exception e) {
                    logger.error("Error al guardar el lote {} (proyectos {}-{}): {}", 
                               i/batchSize + 1, i, endIndex, e.getMessage());
                    throw e;
                }
            }
            
            logger.info("Datos cargados exitosamente: {} proyectos importados.", totalProjects);
        } catch (Exception e) {
            logger.error("Error al cargar los datos del CSV: ", e);
            throw e;
        }
    }
}
