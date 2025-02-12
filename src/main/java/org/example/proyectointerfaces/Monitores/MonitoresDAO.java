package org.example.proyectointerfaces.Monitores;

import org.example.proyectointerfaces.Conexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar la obtención de monitores desde la base de datos.
 */
public class MonitoresDAO {
    private Connection conexion = Conexion.getConexion();

    public MonitoresDAO() {
    }

    /**
     * Obtiene todos los monitores de la base de datos.
     *
     * @return Lista de MonitoresDTO.
     */
    public List<MonitoresDTO> getMonitores() {
        List<MonitoresDTO> listaMonitores = new ArrayList<>();
        String select = "SELECT * from monitores";

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
