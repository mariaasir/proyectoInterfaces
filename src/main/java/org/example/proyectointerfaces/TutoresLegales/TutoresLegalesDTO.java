package org.example.proyectointerfaces.TutoresLegales;

import java.time.LocalDate;

/**
 * Representa un Tutor Legal dentro del sistema.
 * Contiene información personal y de contacto del tutor.
 */
public class TutoresLegalesDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String direccion;
    private String CP;

    private String password;

    /**
     * Constructor para inicializar un objeto TutoresLegalesDTO con la información del tutor.
     *
     * @param nombre          Nombre del tutor.
     * @param apellido        Apellido del tutor.
     * @param dni             Documento Nacional de Identidad del tutor.
     * @param fechaNacimiento Fecha de nacimiento del tutor.
     * @param telefono        Número de teléfono del tutor.
     * @param email           Dirección de correo electrónico del tutor.
     * @param direccion       Dirección de residencia del tutor.
     * @param CP              Código postal de la dirección del tutor.
     * @param password        Contraseña de acceso del tutor.
     */
    public TutoresLegalesDTO(String nombre, String apellido, String dni, LocalDate fechaNacimiento, String telefono, String email, String direccion, String CP, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.CP = CP;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "TutoresLegalesDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", CP='" + CP + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
