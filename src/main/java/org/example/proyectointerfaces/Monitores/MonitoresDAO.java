package org.example.proyectointerfaces.Monitores;

import org.example.proyectointerfaces.Conexion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonitoresDAO {
    private Connection conexion = Conexion.getConexion();

    public MonitoresDAO() {
    }


    public List<MonitoresDTO> getMonitores() {
        List<MonitoresDTO> listaMonitores = new ArrayList<>();
        String select = "SELECT * from monitores";

        //Creamos la sentencia para sacar los datos de cada autor y meterlo en la lista
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(select)) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellidos");
                String password = rs.getString("Password");
                String dni = rs.getString("DNI_NIE");
                LocalDate fechaNacimiento = rs.getObject("Fecha_Nacimiento", LocalDate.class);
                String direccion = rs.getString("Direccion");
                String CP = rs.getString("Codigo_Postal");
                String telefono = rs.getString("Telefono");
                String email = rs.getString("Email");
                String seccion = rs.getString("Seccion_Nombre");

                MonitoresDTO nuevoMonitor = new MonitoresDTO(nombre, apellido,password, dni, fechaNacimiento, direccion, CP, telefono,  email,   seccion);
                nuevoMonitor.setId(id);
                listaMonitores.add(nuevoMonitor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMonitores;
    }
}
