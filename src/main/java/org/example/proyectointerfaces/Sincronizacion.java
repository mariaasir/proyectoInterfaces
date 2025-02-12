package org.example.proyectointerfaces;

import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Monitores.MonitoresDTO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.util.ArrayList;
import java.util.List;
import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Monitores.MonitoresDTO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.util.ArrayList;
import java.util.List;

public class Sincronizacion {
    private List<TutoresLegalesDTO> tutores = new ArrayList<>();
    private List<MonitoresDTO> monitores = new ArrayList<>();
    private TutoresLegalesDAO tutoresDAO;
    private MonitoresDAO monitoresDAO;

    // Constructor con parámetros correctamente tipados
    public Sincronizacion(TutoresLegalesDAO tutoresDAO, MonitoresDAO monitoresDAO) {
        this.tutoresDAO = tutoresDAO;
        this.monitoresDAO = monitoresDAO;
        sincronizar();
    }

    // Método para actualizar las listas
    public void sincronizar() {
        tutores = tutoresDAO.getTutores();
        monitores = monitoresDAO.getMonitores(); // Asegúrate de que este método exista en MonitoresDAO
    }

    // Verifica si un tutor existe por su DNI
    public boolean getTutores(String DNI) {
        for (TutoresLegalesDTO tutor : tutores) {
            if (tutor.getDni().equals(DNI)) {
                return true;
            }

        }
        return false;
    }

    // Verifica si un monitor existe por su DNI
    public boolean getMonitores(String DNI) {
        for (MonitoresDTO monitor : monitores) {
            if (monitor.getDNI_NIE().equals(DNI)) {
                return true;
            }

        }
        return false;
    }

    public boolean comprobarContrasenaTutores(String DNI, String Contrasena) {
        for (TutoresLegalesDTO tutor : tutores) {
            if (tutor.getDni().equals(DNI)) {
                // Si el DNI coincide, comprobamos la contraseña
                return tutor.getPassword().equals(Contrasena);
            }
        }
        // Si no se encuentra ningún tutor con ese DNI, devolvemos false
        return false;
    }


    public boolean comprobarContrasenaMonitores(String DNI, String Contrasena) {
        for (MonitoresDTO monitor : monitores) {
            if (monitor.getDNI_NIE().equals(DNI)) {
                // Si el DNI coincide, comprobamos la contraseña
                return monitor.getPassword().equals(Contrasena);
            }
        }
        // Si no se encuentra ningún tutor con ese DNI, devolvemos false
        return false;
    }

    public TutoresLegalesDTO dameUnTutor(String DNI) {
        for (TutoresLegalesDTO tutor : tutores) {
            if (tutor.getDni().equals(DNI)) {
                return tutor;
            }
        }
        throw new RuntimeException("¿No hay tutor?");
    }


}
