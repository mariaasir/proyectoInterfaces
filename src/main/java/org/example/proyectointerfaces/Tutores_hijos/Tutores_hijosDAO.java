package org.example.proyectointerfaces.Tutores_hijos;

import org.example.proyectointerfaces.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la tabla "tutores_hijos".
 * Permite la obtención de las relaciones entre tutores e hijos desde la base de datos.
 */
public class Tutores_hijosDAO {
    Connection con = Conexion.getConexion();

    /**
     * Constructor vacío de la clase.
     * La conexión a la base de datos se obtiene a través de la clase {@link Conexion}.
     */
    public Tutores_hijosDAO() {
    }

    /**
     * Recupera la lista de relaciones entre tutores e hijos desde la base de datos.
     *
     * @return Lista de objetos con la información de tutores e hijos. En caso de error, retorna una lista vacía.
     */
    public List<Tutores_hijosDTO> getTutores_hijos() {
        List<Tutores_hijosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM tutores_hijos";

        try (Statement sentencia = con.createStatement();
             ResultSet rs = sentencia.executeQuery(sql)) {

            while (rs.next()) {
                int id_padre = rs.getInt("TutorId");
                int id_hijo = rs.getInt("HijoId");

                Tutores_hijosDTO hijo = new Tutores_hijosDTO(id_padre, id_hijo);
                lista.add(hijo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return lista;
    }
}
