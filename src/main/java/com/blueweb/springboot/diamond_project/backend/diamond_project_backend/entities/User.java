    package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities;

    import com.fasterxml.jackson.annotation.JsonProperty;

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
    @Table(name = "sUsuario", schema = "dbo")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idUsuario;

        @NotNull(message = "No puede ser nulo")
        @Size(max = 12, message = "El usuario no puede tener más de 12 caracteres")
        private String usuario;

        @NotNull(message = "No puede ser nulo")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Don't show password in JSON
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

        

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
            result = prime * result + ((pass == null) ? 0 : pass.hashCode());
            result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
            result = prime * result + ((correo == null) ? 0 : correo.hashCode());
            result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            User other = (User) obj;
            if (usuario == null) {
                if (other.usuario != null)
                    return false;
            } else if (!usuario.equals(other.usuario))
                return false;
            if (pass == null) {
                if (other.pass != null)
                    return false;
            } else if (!pass.equals(other.pass))
                return false;
            if (nombreCompleto == null) {
                if (other.nombreCompleto != null)
                    return false;
            } else if (!nombreCompleto.equals(other.nombreCompleto))
                return false;
            if (correo == null) {
                if (other.correo != null)
                    return false;
            } else if (!correo.equals(other.correo))
                return false;
            if (telefono == null) {
                if (other.telefono != null)
                    return false;
            } else if (!telefono.equals(other.telefono))
                return false;
            return true;
        }
        @Override
        public String toString() {
            return "User [idUsuario=" + idUsuario + ", usuario=" + usuario + ", pass=" + pass + ", nombreCompleto="
                    + nombreCompleto + ", correo=" + correo + ", telefono=" + telefono + "]";
        }
        
        
    }
