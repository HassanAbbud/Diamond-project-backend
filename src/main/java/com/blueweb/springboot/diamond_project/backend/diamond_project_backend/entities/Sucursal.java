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
@Table(name = "cSucursal", schema = "dbo")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSucursal;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    @NotNull
    @Size(max = 50, message = "Nombre de sucursal no puede tener más de 50 caracteres")
    private String nombreSucursal;

    @NotNull
    @Size(max = 50, message = "Nombre de ciudad no puede tener más de 50 caracteres")
    private String ciudad;

    @NotNull
    @Size(max = 50, message = "Nombre de estado no puede tener más de 50 caracteres")
    private String estado;

    @NotNull
    private Boolean activo;
    
    @Embedded
    private Auditable audit = new Auditable();

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User usuario;

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    
}
