package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.EmpresaValidation;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Empresa;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.security.CustomUserDetails;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // @Autowired
    // private EmpresaValidation empresaValidation; 

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.findById(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> createEmpresa(@Valid @RequestBody Empresa empresa, BindingResult result, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // empresaValidation.validate(empresa, result);
        if (result.hasErrors()) {
            return validation(result);
        }

        try {
            Long userId = userDetails.getIdUsuario();
            Empresa createdEmpresa = empresaService.create(empresa, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpresa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpresa(@Valid @RequestBody Empresa empresa, BindingResult result, @PathVariable Long id) {
        // empresaValidation.validate(empresa, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Empresa> updatedEmpresa = empresaService.update(id, empresa);
        return updatedEmpresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
