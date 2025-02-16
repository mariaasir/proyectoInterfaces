package org.example.proyectointerfaces.TutoresLegales;

import java.time.LocalDate;

/**
 * Clase DTO que representa un tutor legal.
 * Se utiliza para transferir datos entre las capas de la aplicación.
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
     * Constructor de la clase para inicializar un objeto con los datos del tutor legal.
     * @param nombre          Nombre del tutor.
     * @param apellido        Apellido del tutor.
     * @param dni             DNI del tutor.
     * @param fechaNacimiento Fecha de nacimiento del tutor.
     * @param telefono        Teléfono del tutor.
     * @param email           Correo electrónico del tutor.
     * @param direccion       Dirección del tutor.
     * @param CP              Código Postal del tutor.
     * @param password        Contraseña del tutor.
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

    /**
     * Representación en cadena del objeto TutoresLegalesDTO.
     * @return Una cadena con los valores de los atributos.
     */
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
