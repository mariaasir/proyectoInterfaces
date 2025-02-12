package org.example.proyectointerfaces.Hijos;

import org.example.proyectointerfaces.Conexion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HijosDAO {

    private Connection con = Conexion.getConexion();

    public HijosDAO() {
    }

    public List<HijosDTO> obtenerHijos() {
        List<HijosDTO> hijos = new ArrayList<>();
        String sql = "SELECT * FROM Hijos";

        try (Statement sentencia = con.createStatement();
             ResultSet rs = sentencia.executeQuery(sql)) {

            while (rs.next()) {
                // Extrae los datos y crea los objetos
                int id = rs.getInt("Id");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellidos");
                String dni = rs.getString("DNI_NIE");
                LocalDate fechaNacimiento = rs.getObject("Fecha_Nacimiento", LocalDate.class);
                String telefono_emergencias = rs.getString("Telefono_Emergencia");
                String seccion = rs.getString("Seccion_Nombre");
                String direccion = rs.getString("Direccion");
                String CP = rs.getString("Codigo_Postal");

                HijosDTO nuevoHijo = new HijosDTO(nombre, apellido, dni, fechaNacimiento, direccion, CP, telefono_emergencias, seccion);
                nuevoHijo.setId(id);
                hijos.add(nuevoHijo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return hijos;
    }
}
