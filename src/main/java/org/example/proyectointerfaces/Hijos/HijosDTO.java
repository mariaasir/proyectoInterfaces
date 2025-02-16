package org.example.proyectointerfaces.Hijos;


import java.time.LocalDate;

/**
 * Clase DTO para representar la información de un hijo.
 */
public class HijosDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private String dNI_NIE;
    private LocalDate fecha_Nacimiento;
    private String direccion;
    private String codigo_Postal;
    private String telefono_Emergencia;
    private String seccion_Nombre;

    public HijosDTO() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto HijosDTO.
     *
     * @param nombre              Nombre del hijo.
     * @param apellidos           Apellidos del hijo.
     * @param dNI_NIE             Documento de identificación (DNI o NIE).
     * @param fecha_Nacimiento    Fecha de nacimiento.
     * @param direccion           Dirección de residencia.
     * @param codigo_Postal       Código postal de la dirección.
     * @param telefono_Emergencia Teléfono de contacto en caso de emergencia.
     * @param seccion_Nombre      Nombre de la sección a la que pertenece.
     */
    public HijosDTO(String nombre, String apellidos, String dNI_NIE, LocalDate fecha_Nacimiento, String direccion, String codigo_Postal, String telefono_Emergencia, String seccion_Nombre) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dNI_NIE = dNI_NIE;
        this.fecha_Nacimiento = fecha_Nacimiento;
        this.direccion = direccion;
        this.codigo_Postal = codigo_Postal;
        this.telefono_Emergencia = telefono_Emergencia;
        this.seccion_Nombre = seccion_Nombre;
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

    public String getdNI_NIE() {
        return dNI_NIE;
    }

    public void setdNI_NIE(String dNI_NIE) {
        this.dNI_NIE = dNI_NIE;
    }

    public LocalDate getFecha_Nacimiento() {
        return fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(LocalDate fecha_Nacimiento) {
        this.fecha_Nacimiento = fecha_Nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigo_Postal() {
        return codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) {
        this.codigo_Postal = codigo_Postal;
    }

    public String getTelefono_Emergencia() {
        return telefono_Emergencia;
    }

    public void setTelefono_Emergencia(String telefono_Emergencia) {
        this.telefono_Emergencia = telefono_Emergencia;
    }

    public String getSeccion_Nombre() {
        return seccion_Nombre;
    }

    public void setSeccion_Nombre(String seccion_Nombre) {
        this.seccion_Nombre = seccion_Nombre;
    }

    /**
     * Devuelve una representación en cadena del objeto HijosDTO.
     *
     * @return Cadena con los valores de los atributos del objeto.
     */
    @Override
    public String toString() {
        return "HijosDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dNI_NIE='" + dNI_NIE + '\'' +
                ", fecha_Nacimiento=" + fecha_Nacimiento +
                ", direccion='" + direccion + '\'' +
                ", codigo_Postal='" + codigo_Postal + '\'' +
                ", telefono_Emergencia='" + telefono_Emergencia + '\'' +
                ", seccion_Nombre='" + seccion_Nombre + '\'' +
                '}';
    }
}
