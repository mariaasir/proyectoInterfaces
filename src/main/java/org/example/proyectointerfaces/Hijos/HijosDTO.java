package org.example.proyectointerfaces.Hijos;

import java.time.LocalDate;

public class HijosDTO {
    int id;
    String nombre;
    String apellidos;
    String dNI_NIE;
    LocalDate fecha_Nacimiento;
    String direccion;
    String codigo_Postal;
    String telefono_Emergencia;
    String seccion_Nombre;

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
}
