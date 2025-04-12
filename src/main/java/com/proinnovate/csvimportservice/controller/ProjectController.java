package com.proinnovate.csvimportservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proinnovate.csvimportservice.model.Project;
import com.proinnovate.csvimportservice.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjects(Pageable pageable) {
        return ResponseEntity.ok(projectService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);
        return project.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/fondo/{fondo}")
    public ResponseEntity<List<Project>> getProjectsByFondo(@PathVariable String fondo) {
        List<Project> projects = projectService.findByFondo(fondo);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/search/departamento/{departamento}")
    public ResponseEntity<List<Project>> getProjectsByDepartamento(@PathVariable String departamento) {
        List<Project> projects = projectService.findByDepartamento(departamento);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/search/anio/{anio}")
    public ResponseEntity<List<Project>> getProjectsByAnio(@PathVariable Integer anio) {
        List<Project> projects = projectService.findByAnio(anio);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/search/titulo/{titulo}")
    public ResponseEntity<List<Project>> searchProjectsByTitulo(@PathVariable String titulo) {
        List<Project> projects = projectService.searchProjectsByTitulo(titulo);
        return ResponseEntity.ok(projects);
    }

}
