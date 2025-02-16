package org.example.proyectointerfaces.TutoresLegales;

import org.example.proyectointerfaces.Conexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para la gestión de tutores legales en la base de datos.
 * Proporciona métodos para insertar y recuperar tutores legales.
 */
public class TutoresLegalesDAO {
    private Connection conexion = Conexion.getConexion();

    /**
     * Constructor vacío de la clase TutoresLegalesDAO.
     */
    public TutoresLegalesDAO() {
    }

    /**
     * Inserta un nuevo tutor legal en la base de datos.
     *
     * @param tutoresLegal Objeto TutoresLegalesDTO que contiene los datos del tutor a insertar.
     * @throws RuntimeException se lanza si ocurre un error durante la inserción.
     */
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

    /**
     * Recupera la lista de todos los tutores legales almacenados en la base de datos.
     *
     * @return Lista de objetos TutoresLegalesDTO con los datos de cada tutor.
     */
    public List<TutoresLegalesDTO> getTutores() {
        List<TutoresLegalesDTO> listaTutores = new ArrayList<>();
        String select = "SELECT * from tutoreslegales";

        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(select)) {

            while (rs.next()) {
                // Extrae los datos y crea los objetos
                int id = rs.getInt("Id");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellidos");
                String dni = rs.getString("DNI_NIE");
                LocalDate fechaNacimiento = rs.getObject("Fecha_Nacimiento", LocalDate.class);
                String telefono = rs.getString("Telefono");
                String email = rs.getString("Email");
                String direccion = rs.getString("Direccion");
                String CP = rs.getString("Codigo_Postal");
                String password = rs.getString("Password");

                TutoresLegalesDTO nuevoTutor = new TutoresLegalesDTO(nombre, apellido, dni, fechaNacimiento, telefono, email, direccion, CP, password);
                nuevoTutor.setId(id);
                listaTutores.add(nuevoTutor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return listaTutores;
    }
}
