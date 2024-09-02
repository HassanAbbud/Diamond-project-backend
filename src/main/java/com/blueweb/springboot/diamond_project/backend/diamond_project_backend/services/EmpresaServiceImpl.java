package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Empresa;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.EmpresaRepository;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.UserRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService{

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<Empresa> findAll() {
        return (List<Empresa>) empresaRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id); 
    }

    //Creates new Empresa and sets existing user id
    @Override
    @Transactional
    public Empresa create(Empresa empresa, Long userId) {
        // Find the User by ID
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            empresa.setUsuario(user);

            return empresaRepository.save(empresa);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found.");
        }
    }

    @Override
    @Transactional
    public Optional<Empresa> update(Long id, Empresa empresa) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);

        if (optionalEmpresa.isPresent()) {
            Empresa empresaDb = optionalEmpresa.orElseThrow();

            empresaDb.setClaveEmpresa(empresa.getClaveEmpresa());
            empresaDb.setNombreEmpresa(empresa.getNombreEmpresa());
            empresaDb.setActivo(empresa.isActivo());
            empresaDb.setUsuario(empresaDb.getUsuario());
            
            empresaRepository.save(empresaDb);
            return Optional.of(empresaDb);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Empresa> delete(Long id) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        optionalEmpresa.ifPresent(empresaDb -> {
            empresaRepository.delete(empresaDb);
        });

        return Optional.empty();
    }

}
