package org.example.proyectointerfaces.Monitores;

import java.time.LocalDate;

/**
 * Contiene información personal y de contacto del Monitor.
 * Representa un monitor dentro del sistema.
 */
public class MonitoresDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private String password;
    private String DNI_NIE;
    private LocalDate fecha_nacimiento;
    private String direccion;
    private String CP;
    private String telefono;
    private String email;
    private String seccion;

    /**
     * Constructor de la clase MonitoresDTO. Inicializa todos los atributos con los valores proporcionados.
     *
     * @param nombre           Nombre del monitor.
     * @param apellidos        Apellidos del monitor.
     * @param password         Contraseña del monitor.
     * @param DNI_NIE          DNI o NIE del monitor.
     * @param fecha_nacimiento Fecha de nacimiento del monitor.
     * @param direccion        Dirección del monitor.
     * @param CP               Código postal del monitor.
     * @param telefono         Teléfono del monitor.
     * @param email            Email del monitor.
     * @param seccion          Sección asignada al monitor.
     */
    public MonitoresDTO(String nombre, String apellidos, String password, String DNI_NIE, LocalDate fecha_nacimiento, String direccion, String CP, String telefono, String email, String seccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.DNI_NIE = DNI_NIE;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.CP = CP;
        this.telefono = telefono;
        this.email = email;
        this.seccion = seccion;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDNI_NIE() {
        return DNI_NIE;
    }

    public void setDNI_NIE(String DNI_NIE) {
        this.DNI_NIE = DNI_NIE;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    /**
     * Metodo toString que devuelve una representación textual del objeto.
     *
     * @return Una cadena que describe el objeto MonitoresDTO.
     */
    @Override
    public String toString() {
        return "MonitoresDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", password='" + password + '\'' +
                ", DNI_NIE='" + DNI_NIE + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", direccion='" + direccion + '\'' +
                ", CP='" + CP + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", seccion='" + seccion + '\'' +
                '}';
    }
}
