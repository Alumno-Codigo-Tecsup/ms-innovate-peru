package com.proinnovate.csvimportservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proinnovate.csvimportservice.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByFondoContainingIgnoreCase(String fondo);
    List<Project> findByDepartamentoContainingIgnoreCase(String departamento);
    List<Project> findByAnio(Integer anio);
    List<Project> findByConcursoContainingIgnoreCase(String concurso);
    List<Project> findByTituloContainingIgnoreCase(String titulo);
}
