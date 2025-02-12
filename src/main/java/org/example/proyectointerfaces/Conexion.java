package org.example.proyectointerfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de la conexión
 */
public class Conexion {
    /**
     * Párametros para la conexión
     */
    private static final String USUARIO = "root";
    private static final String PASSWD = "";
    private static final String URL = "jdbc:mariadb://localhost:3306/orion";

    private static Connection conexion = null;

    /**
     * Constructor privado para evitar instancias múltiples
     */
    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); //Cargar el driver de MariaDB
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWD); //Intentar la conexión
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: No se encontró el driver de MariaDB.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a la base de datos: " + e.getMessage(), e);
        }
    }

    /**
     * Metodo para obtener la conexión (Singleton)
     *
     * @return la conexión
     */
    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();  //Crear la conexión si no existe
        }
        return conexion;
    }


    /**
     * Metodo para cerrar la conexión
     */
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
