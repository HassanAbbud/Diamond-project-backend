package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotNull(message = "No puede ser nulo")
    @Size(max = 12, message = "El usuario no puede tener más de 12 caracteres")
    private String usuario;

    @NotNull(message = "No puede ser nulo")
    private String pass;

    @NotNull(message = "No puede ser nulo")
    private String nombreCompleto;

    @Email(message = "Tiene que tener formato de email")
    @Size(max = 50, message = "El correo no puede tener más de 50 caracteres")
    private String correo;

    @Pattern(regexp = "\\d{10}", message = "Numero tiene que contener 10 dígitos.")
    private String telefono;

    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long id) {
        this.idUsuario = id;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String username) {
        this.usuario = username;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String password) {
        this.pass = password;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
