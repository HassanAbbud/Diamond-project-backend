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

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Sucursal;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.security.CustomUserDetails;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services.SucursalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
public class SucursalController {

    @Autowired 
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> getAllSucursales() {
        List<Sucursal> sucursales = sucursalService.findAll();
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Long id) {
        Optional<Sucursal> sucursal = sucursalService.findById(id);
        return sucursal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Create a new Sucursal with an existing Empresa
    //since empresa cant be null body must have empresa{}
    @PostMapping("/empresa-exists/{empresaId}")
    public ResponseEntity<?> createSucursalWithExistingEmpresa(
        @Valid @RequestBody Sucursal sucursal,
        BindingResult result,
        @PathVariable("empresaId") Long empresaId,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (result.hasErrors()) {
            return validation(result);
        }

        try {
            Long userId = userDetails.getIdUsuario();
            Sucursal createdSucursal = sucursalService.createWithExistingEmpresa(sucursal, userId, empresaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSucursal);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    //Create a new Sucursal and a new Empresa
    //since empresa cant be null body must have empresa{}
    @PostMapping("/new")
    public ResponseEntity<?> createSucursalWithNewEmpresa(@Valid @RequestBody Sucursal sucursal, BindingResult result, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // sucursalValidation.validate(sucursal, result);
        if (result.hasErrors()) {
            return validation(result);
        }

        try {
            Long userId = userDetails.getIdUsuario();
            Sucursal createdSucursal = sucursalService.createWithNewEmpresa(sucursal, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSucursal);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }    

    //since empresa cant be null body must have empresa{}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSucursal(@Valid @RequestBody Sucursal sucursal, BindingResult result, @PathVariable Long id) {
        // sucursalValidation.validate(sucursal, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Sucursal> updatedSucursal = sucursalService.update(id, sucursal);
        return updatedSucursal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        sucursalService.delete(id);
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
