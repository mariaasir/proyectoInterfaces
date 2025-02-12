package org.example.proyectointerfaces;

import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Monitores.MonitoresDTO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la gestión y sincronización de la información de Tutores legales y Monitores.
 */
public class Sincronizacion {
    private List<TutoresLegalesDTO> tutores = new ArrayList<>();
    private List<MonitoresDTO> monitores = new ArrayList<>();
    private TutoresLegalesDAO tutoresDAO;
    private MonitoresDAO monitoresDAO;

    /**
     * Constructor de la clase Sincronizacion.
     *
     * @param tutoresDAO   DAO para la gestión de tutores legales.
     * @param monitoresDAO DAO para la gestión de monitores.
     */
    public Sincronizacion(TutoresLegalesDAO tutoresDAO, MonitoresDAO monitoresDAO) {
        this.tutoresDAO = tutoresDAO;
        this.monitoresDAO = monitoresDAO;
        sincronizar();
    }

    /**
     * Metodo para actualizar las listas de tutores y monitores con los datos de la base de datos.
     */
    public void sincronizar() {
        tutores = tutoresDAO.getTutores();
        monitores = monitoresDAO.getMonitores();
    }

    /**
     * Verifica si un tutor legal existe en la base de datos según su DNI.
     *
     * @param DNI para identificar al turor legal
     * @return true si el tutor existe, false en caso contrario.
     */
    public boolean getTutores(String DNI) {
        for (TutoresLegalesDTO tutor : tutores) {
            if (tutor.getDni().equals(DNI)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un monitor existe en la base de datos según su DNI/NIE.
     *
     * @param DNI para identificar al monitor.
     * @return true si el monitor existe, false en caso contrario.
     */
    public boolean getMonitores(String DNI) {
        for (MonitoresDTO monitor : monitores) {
            if (monitor.getDNI_NIE().equals(DNI)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si la contraseña de un tutor es correcta.
     *
     * @param DNI        para identificar al tutor.
     * @param Contrasena Contraseña ingresada para verificación.
     * @return true si la contraseña es correcta, false en caso contrario.
     */
    public boolean comprobarContrasenaTutores(String DNI, String Contrasena) {
        for (TutoresLegalesDTO tutor : tutores) {
            if (tutor.getDni().equals(DNI)) {
                return tutor.getPassword().equals(Contrasena);
            }
        }
        return false;
    }

    /**
     * Comprueba si la contraseña de un monitor es correcta.
     *
     * @param DNI        para identificar al monitor.
     * @param Contrasena Contraseña ingresada para verificación.
     * @return true si la contraseña es correcta, false en caso contrario.
     */
    public boolean comprobarContrasenaMonitores(String DNI, String Contrasena) {
        for (MonitoresDTO monitor : monitores) {
            if (monitor.getDNI_NIE().equals(DNI)) {
                return monitor.getPassword().equals(Contrasena);
            }
        }
        return false;
    }
}
