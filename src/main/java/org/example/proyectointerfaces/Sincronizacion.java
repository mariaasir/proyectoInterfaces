package org.example.proyectointerfaces;

import org.example.proyectointerfaces.Hijos.HijosDAO;
import org.example.proyectointerfaces.Hijos.HijosDTO;
import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Monitores.MonitoresDTO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.util.ArrayList;
import java.util.List;

import org.example.proyectointerfaces.Tutores_hijos.Tutores_hijosDAO;
import org.example.proyectointerfaces.Tutores_hijos.Tutores_hijosDTO;

/**
 * Clase encargada de la sincronización de datos entre las entidades relacionadas
 * con tutores, monitores, hijos y la relación entre tutores e hijos.
 */
public class Sincronizacion {
    private List<TutoresLegalesDTO> tutores = new ArrayList<>();
    private List<MonitoresDTO> monitores = new ArrayList<>();
    private List<HijosDTO> hijos = new ArrayList<>();
    private List<Tutores_hijosDTO> tutores_hijos = new ArrayList<>();

    private Tutores_hijosDAO tutoresHijosDAO;
    private HijosDAO hijosDAO;
    private TutoresLegalesDAO tutoresDAO;
    private MonitoresDAO monitoresDAO;

    /**
     * Constructor que inicializa los objetos DAO y sincroniza los datos.
     *
     * @param tutoresDAO      DAO para gestionar los tutores legales.
     * @param monitoresDAO    DAO para gestionar los monitores.
     * @param hijosDAO        DAO para gestionar los hijos.
     * @param tutoresHijosDAO DAO para gestionar la relación entre tutores e hijos.
     */
    public Sincronizacion(TutoresLegalesDAO tutoresDAO, MonitoresDAO monitoresDAO, HijosDAO hijosDAO, Tutores_hijosDAO tutoresHijosDAO) {
        this.tutoresDAO = tutoresDAO;
        this.monitoresDAO = monitoresDAO;
        this.tutoresHijosDAO = tutoresHijosDAO;
        this.hijosDAO = hijosDAO;
        sincronizar();
    }

    /**
     * Metodo que actualiza las listas de tutores, monitores, hijos y relación de tutores e hijos.
     */
    public void sincronizar() {
        tutores = tutoresDAO.getTutores();
        monitores = monitoresDAO.getMonitores(); // Asegúrate de que este método exista en MonitoresDAO
        hijos = hijosDAO.obtenerHijos();
        tutores_hijos = tutoresHijosDAO.getTutores_hijos();
    }

    /**
     * Verifica si existe un tutor con el DNI proporcionado.
     *
     * @param DNI El DNI del tutor.
     * @return true si el tutor existe, false si no.
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
     * Verifica si existe un monitor con el DNI proporcionado.
     *
     * @param DNI El DNI del monitor.
     * @return true si el monitor existe, false si no.
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
     * Verifica si la contraseña del tutor es correcta.
     *
     * @param DNI        El DNI del tutor.
     * @param Contrasena La contraseña proporcionada.
     * @return true si la contraseña es correcta, false si no.
     */
    public boolean comprobarContrasenaTutores(String DNI, String Contrasena) {
        for (TutoresLegalesDTO tutor : tutores) {
            if (tutor.getDni().equals(DNI)) {
                // Si el DNI coincide, comprobamos la contraseña
                return tutor.getPassword().equals(Contrasena);
            }
        }
        return false;
    }

    /**
     * Verifica si la contraseña del monitor es correcta.
     *
     * @param DNI        El DNI del monitor.
     * @param Contrasena La contraseña proporcionada.
     * @return true si la contraseña es correcta, false si no.
     */
    public boolean comprobarContrasenaMonitores(String DNI, String Contrasena) {
        for (MonitoresDTO monitor : monitores) {
            if (monitor.getDNI_NIE().equals(DNI)) {
                // Si el DNI coincide, comprobamos la contraseña
                return monitor.getPassword().equals(Contrasena);
            }
        }
        return false;
    }

    /**
     * Devuelve un tutor según su DNI.
     *
     * @param DNI El DNI del tutor.
     * @return El tutor correspondiente.
     * @throws RuntimeException Si no se encuentra el tutor.
     */
    public TutoresLegalesDTO dameUnTutor(String DNI) {
        for (TutoresLegalesDTO tutor : tutores) {
            if (tutor.getDni().equals(DNI)) {
                return tutor;
            }
        }
        throw new RuntimeException("¿No hay tutor?");
    }

    /**
     * Devuelve los hijos de un padre identificado por su ID.
     *
     * @param id El ID del padre.
     * @return Una lista de hijos correspondientes.
     */
    public List<HijosDTO> devolverHijosDeUnPadre(int id) {
        List<HijosDTO> hijos = new ArrayList<>();
        List<Integer> idsHijos = new ArrayList<>();
        for (Tutores_hijosDTO tuhi : tutoresHijosDAO.getTutores_hijos()) {
            if (tuhi.getId_padre() == id) {
                idsHijos.add(tuhi.getId_hijo());
            }
        }
        for (HijosDTO hijo : hijosDAO.obtenerHijos()) {
            if (idsHijos.contains(hijo.getId())) {
                hijos.add(hijo);
            }
        }
        return hijos;
    }
}
