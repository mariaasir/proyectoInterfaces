package org.example.proyectointerfaces.TutoresLegales;

import org.example.proyectointerfaces.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TutoresLegalesDAO {
    private Connection conexion = Conexion.getConexion();;


    public TutoresLegalesDAO() {
    }

    public void insertTutorLegal(TutoresLegalesDTO tutoresLegal) {
        String insertSql = "INSERT INTO tutoreslegales (Nombre, Apellidos, Password, DNI_NIE, Fecha_Nacimiento, Direccion, Codigo_Postal, Telefono, Email) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement insertStmt = conexion.prepareStatement(insertSql)) {
            insertStmt.setString(1, tutoresLegal.getNombre());
            insertStmt.setString(2, tutoresLegal.getApellido());
            insertStmt.setString(3, tutoresLegal.getPassword());
            insertStmt.setString(4, tutoresLegal.getDni());
            insertStmt.setDate(5, Date.valueOf(tutoresLegal.getFechaNacimiento()));
            insertStmt.setString(6, tutoresLegal.getDireccion());
            insertStmt.setString(7, tutoresLegal.getCP());
            insertStmt.setString(8, tutoresLegal.getTelefono());
            insertStmt.setString(9, tutoresLegal.getEmail());

            // Aquí agregamos el executeUpdate() para ejecutar la inserción
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            // Manejo del error: puedes imprimir el stack trace o lanzar una excepción
            throw new RuntimeException("Error al insertar el tutor legal", e);
        }
    }

}
