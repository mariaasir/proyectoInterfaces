package org.example.proyectointerfaces.Tutores_hijos;

public class Tutores_hijosDTO {
    int id_padre;
    int id_hijo;

    public Tutores_hijosDTO() {
    }

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

    @Override
    public String toString() {
        return "Tutores_hijosDTO{" +
                "id_padre=" + id_padre +
                ", id_hijo=" + id_hijo +
                '}';
    }
}
