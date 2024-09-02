package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Auditable {

    @NotNull
    private LocalDateTime fechaAlta;
    
    private LocalDateTime fechaBaja;

    @NotNull
    private LocalDateTime fechaServidor;

    @PrePersist
    public void prePersist() {
        System.out.println("Entity object lifecycle event pre-persist");
        this.fechaAlta = LocalDateTime.now();
        this.fechaServidor = LocalDateTime.now();
    }
    
    //Consultar si este dato se refiere a cuando un producto es borrado (post-remove)
    @PreRemove
    public void postRemove(){
        System.out.println("Entity object lifecycle event pre-remove");
        this.fechaBaja = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("Entity object lifecycle event pre-update");
        this.fechaServidor = LocalDateTime.now();
    }
    
    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public LocalDateTime getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(LocalDateTime fechaServidor) {
        this.fechaServidor = fechaServidor;
    }
}
