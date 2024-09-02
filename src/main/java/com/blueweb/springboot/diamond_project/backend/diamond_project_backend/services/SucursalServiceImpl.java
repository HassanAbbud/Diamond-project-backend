package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Empresa;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Sucursal;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.EmpresaRepository;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.SucursalRepository;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.UserRepository;

@Service
public class SucursalServiceImpl implements SucursalService{

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public List<Sucursal> findAll() {
        return (List<Sucursal>) sucursalRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }

    //Create Sucursal with existing Empresa
    @Override
    @Transactional
    public Sucursal createWithExistingEmpresa(Sucursal sucursal, Long userId, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa with ID " + empresaId + " not found."));
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found."));

        sucursal.setEmpresa(empresa);
        sucursal.setUsuario(user);

        return sucursalRepository.save(sucursal);
    }

    //Create Sucursal with new Empresa
    @Override
    @Transactional
    public Sucursal createWithNewEmpresa(Sucursal sucursal, Long userId) {
        // Find the User by ID
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            sucursal.setUsuario(user);

            return sucursalRepository.save(sucursal);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found.");
        }
    }

    @Override
    @Transactional
    public Optional<Sucursal> update(Long id, Sucursal sucursal) {
        Optional<Sucursal> optionalSucursal = sucursalRepository.findById(id);

        if (optionalSucursal.isPresent()) {
            Sucursal sucursalDb = optionalSucursal.orElseThrow();

            sucursalDb.setNombreSucursal(sucursal.getNombreSucursal());
            sucursalDb.setEstado(sucursal.getEstado());
            sucursalDb.setActivo(sucursal.isActivo());
            sucursalDb.setUsuario(sucursalDb.getUsuario());
            
            sucursalRepository.save(sucursalDb);
            return Optional.of(sucursalDb);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Sucursal> delete(Long id) {
        Optional<Sucursal> optionalSucursal = sucursalRepository.findById(id);
        optionalSucursal.ifPresent(sucursalDb -> {
            sucursalRepository.delete(sucursalDb);
        });

        return Optional.empty();
    }

}
