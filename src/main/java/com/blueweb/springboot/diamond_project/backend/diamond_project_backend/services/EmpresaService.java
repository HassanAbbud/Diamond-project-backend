package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;
import java.util.Optional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Empresa;

public interface EmpresaService {
    List<Empresa> findAll();

    Optional<Empresa> findById(Long id);

    Empresa create(Empresa empresa, Long userId);

    Optional<Empresa> update(Long id, Empresa empresa);

    Optional<Empresa> delete(Long id);
}
