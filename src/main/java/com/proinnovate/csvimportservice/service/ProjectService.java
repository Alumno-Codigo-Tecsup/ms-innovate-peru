package com.proinnovate.csvimportservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proinnovate.csvimportservice.model.Project;
import com.proinnovate.csvimportservice.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> findByFondo(String fondo) {
        return projectRepository.findByFondoContainingIgnoreCase(fondo);
    }

    public List<Project> findByDepartamento(String departamento) {
        return projectRepository.findByDepartamentoContainingIgnoreCase(departamento);
    }

    public List<Project> findByAnio(Integer anio) {
        return projectRepository.findByAnio(anio);
    }

    public List<Project> findByConcurso(String concurso) {
        return projectRepository.findByConcursoContainingIgnoreCase(concurso);
    }

    public List<Project> searchProjectsByTitulo(String titulo) {
        return projectRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

/*    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + id));

        project.setFondo(projectDetails.getFondo());
        project.setConcurso(projectDetails.getConcurso());
        project.setAnio(projectDetails.getAnio());
        project.setContrato(projectDetails.getContrato());
        project.setTitulo(projectDetails.getTitulo());
        project.setFechaInicio(projectDetails.getFechaInicio());
        project.setFechaFin(projectDetails.getFechaFin());
        project.setNombreSolicitante(projectDetails.getNombreSolicitante());
        project.setDistrito(projectDetails.getDistrito());
        project.setProvincia(projectDetails.getProvincia());
        project.setDepartamento(projectDetails.getDepartamento());
        project.setUbigeo(projectDetails.getUbigeo());
        project.setMontoRNR(projectDetails.getMontoRNR());
        project.setMontoFinanciero(projectDetails.getMontoFinanciero());
        project.setMontoNoFinanciero(projectDetails.getMontoNoFinanciero());
        project.setMoneda(projectDetails.getMoneda());
        project.setFechaCorte(projectDetails.getFechaCorte());

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + id));

        projectRepository.delete(project);
    }*/
}
