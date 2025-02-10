package org.example.proyectointerfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Parámetros de conexión
    private static final String USUARIO = "root";
    private static final String PASSWD = "";
    private static final String URL = "jdbc:mariadb://localhost:3306/orion";

    // Singleton para la conexión
    private static Connection conexion = null;

    //Constructor privado para evitar instancias múltiples
    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); //Cargar el driver de MariaDB
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWD); //Intentar la conexión
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: No se encontró el driver de MariaDB.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a la base de datos: " + e.getMessage(), e);
        }
    }

    //Método para obtener la conexión (Singleton)
    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();  //Crear la conexión si no existe
        }
        return conexion;
    }

    //Método para cerrar la conexión (opcional)
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
