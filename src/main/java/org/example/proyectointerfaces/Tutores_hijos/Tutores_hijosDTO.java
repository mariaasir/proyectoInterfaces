package org.example.proyectointerfaces.Tutores_hijos;

/**
 * Clase DTO que representa la relación entre un tutor y su hijo.
 */
public class Tutores_hijosDTO {
    int id_padre;
    int id_hijo;

    /**
     * Constructor vacío de la clase.
     */
    public Tutores_hijosDTO() {
    }

    /**
     * Constructor con parámetros para inicializar la relación tutor-hijo.
     *
     * @param id_padre Identificador del tutor.
     * @param id_hijo  Identificador del hijo.
     */
    public Tutores_hijosDTO(int id_padre, int id_hijo) {
        this.id_padre = id_padre;
        this.id_hijo = id_hijo;
    }

    public int getId_padre() {
        return id_padre;
    }

    public void setId_padre(int id_padre) {
        this.id_padre = id_padre;
    }

    public int getId_hijo() {
        return id_hijo;
    }

    public void setId_hijo(int id_hijo) {
        this.id_hijo = id_hijo;
    }

    /**
     * Representación en cadena del objeto Tutores_hijosDTO.
     *
     * @return Una cadena con los valores de id_padre e id_hijo.
     */
    @Override
    public String toString() {
        return "Tutores_hijosDTO{" +
                "id_padre=" + id_padre +
                ", id_hijo=" + id_hijo +
                '}';
    }
}
