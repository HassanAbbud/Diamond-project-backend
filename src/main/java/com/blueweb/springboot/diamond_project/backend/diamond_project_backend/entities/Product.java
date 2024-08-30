package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities;

import java.math.BigDecimal;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cProducto", schema = "dbo")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    //GrupoProducto entity should be created for filtering Products by their group
    // @NotNull
    // @ManyToOne
    // private GrupoProducto idGrupoProducto;

    @NotNull
    private String nombreProducto;

    @Digits(integer = 8, fraction = 2)  // Precision: 10, Scale: 2
    @DecimalMin(value = "0.00", message = "Precio debe ser mayor o igual a 0")
    @DecimalMax(value = "99999999.99", message = "Precio debe ser menor o igual a 99999999.99")
    private BigDecimal precioCompra;

    @NotNull
    @Digits(integer = 8, fraction = 2)  // Precision: 10, Scale: 2
    @DecimalMin(value = "0.00", message = "Precio debe ser mayor o a igual 0")
    @DecimalMax(value = "99999999.99", message = "Precio debe ser menor o igual a 99999999.99")
    private BigDecimal precioVenta;
    
    @NotNull
    private Boolean activo;

    @Embedded
    private Auditable auidt = new Auditable();

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User usuario;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    // public Long getIdGrupoProducto() {
    //     return idGrupoProducto;
    // }

    // public void setIdGrupoProducto(Long idGrupoProducto) {
    //     this.idGrupoProducto = idGrupoProducto;
    // }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Number getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Number getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Auditable getAuidt() {
        return auidt;
    }

    public void setAuidt(Auditable auidt) {
        this.auidt = auidt;
    }

    public User getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(User usuario) {
        this.usuario = usuario;
    }


}
