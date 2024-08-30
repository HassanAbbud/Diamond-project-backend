package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;
import java.util.Optional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Sucursal;

public interface SucursalService {
    List<Sucursal> findAll();

    Optional<Sucursal> findById(Long id);

    Sucursal create(Sucursal sucursal);

    Optional<Sucursal> update(Long id);

    Optional<Sucursal> delete(Long id);
}
