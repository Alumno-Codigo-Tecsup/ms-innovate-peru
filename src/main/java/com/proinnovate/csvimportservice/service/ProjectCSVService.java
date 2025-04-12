package com.proinnovate.csvimportservice.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.proinnovate.csvimportservice.model.Project;
import com.proinnovate.csvimportservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class ProjectCSVService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> importCSV(MultipartFile file) throws Exception{
        if(file.isEmpty()){
            throw new Exception("File is empty");
        }

        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<Project> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Project.class);

            CsvToBean<Project> csvToBean = new CsvToBeanBuilder<Project>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .build();

            List<Project> projects = csvToBean.parse();

            return projectRepository.saveAll(projects);
        } catch (Exception e){
            throw new Exception("Error processing the file" + e.getMessage());
        }
    }

}
