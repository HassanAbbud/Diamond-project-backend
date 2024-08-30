package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cEmpresa", schema = "dbo")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @NotNull 
    @Size(min = 3, max = 3, message = "Clave solo puede contener 3 caracteres")
    private String claveEmpresa;

    @NotNull
    @Size(max = 50, message = "Nombre de empresa no puede tener más de 50 caracteres")
    private String nombreEmpresa;

    @NotNull
    private boolean activo;

    @Embedded
    private Auditable audit = new Auditable();

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User idUsuario;

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getClaveEmpresa() {
        return claveEmpresa;
    }

    public void setClaveEmpresa(String claveEmpresa) {
        this.claveEmpresa = claveEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Auditable getAudit() {
        return audit;
    }

    public void setAudit(Auditable audit) {
        this.audit = audit;
    }

    public User getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(User idUsuario) {
        this.idUsuario = idUsuario;
    }

}
