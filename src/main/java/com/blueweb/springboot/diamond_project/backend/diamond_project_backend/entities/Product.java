package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
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
    private Number precioCompra;

    @NotNull
    @Digits(integer = 8, fraction = 2)  // Precision: 10, Scale: 2
    @DecimalMin(value = "0.00", message = "Precio debe ser mayor o a igual 0")
    @DecimalMax(value = "99999999.99", message = "Precio debe ser menor o igual a 99999999.99")
    private Number precioVenta;
    
    @NotNull
    private Boolean activo;

    @Embedded
    private Auditable auidt = new Auditable();

    @ManyToOne
    private User idUsuario;

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

    public void setPrecioCompra(Number precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Number getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Number precioVenta) {
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
        return idUsuario;
    }

    public void setIdUsuario(User idUsuario) {
        this.idUsuario = idUsuario;
    }


}
