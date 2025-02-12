package org.example.proyectointerfaces.Hijos;

import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.time.LocalDate;
import java.util.List;

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
