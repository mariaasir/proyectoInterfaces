package org.example.proyectointerfaces;

import org.example.proyectointerfaces.Hijos.HijosDAO;
import org.example.proyectointerfaces.Hijos.HijosDTO;
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
import org.example.proyectointerfaces.Tutores_hijos.Tutores_hijosDAO;
import org.example.proyectointerfaces.Tutores_hijos.Tutores_hijosDTO;

import java.util.ArrayList;
import java.util.List;

public class Sincronizacion {
    private List<TutoresLegalesDTO> tutores = new ArrayList<>();
    private List<MonitoresDTO> monitores = new ArrayList<>();
    private List<HijosDTO> hijos = new ArrayList<>();
    private List<Tutores_hijosDTO> tutores_hijos = new ArrayList<>();

    private Tutores_hijosDAO tutoresHijosDAO;
    private HijosDAO hijosDAO;
    private TutoresLegalesDAO tutoresDAO;
    private MonitoresDAO monitoresDAO;

    // Constructor con parámetros correctamente tipados
    public Sincronizacion(TutoresLegalesDAO tutoresDAO, MonitoresDAO monitoresDAO, HijosDAO hijosDAO, Tutores_hijosDAO tutoresHijosDAO) {
        this.tutoresDAO = tutoresDAO;
        this.monitoresDAO = monitoresDAO;
        this.tutoresHijosDAO = tutoresHijosDAO;
        this.hijosDAO = hijosDAO;
        sincronizar();
    }

    // Método para actualizar las listas
    public void sincronizar() {
        tutores = tutoresDAO.getTutores();
        monitores = monitoresDAO.getMonitores(); // Asegúrate de que este método exista en MonitoresDAO
        hijos = hijosDAO.obtenerHijos();
        tutores_hijos = tutoresHijosDAO.getTutores_hijos();
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

    public List<HijosDTO> devolverHijosDeUnPadre(int id){
        List<HijosDTO> hijos = new ArrayList<>();
        List<Integer> idsHijos = new ArrayList<>();
        for (Tutores_hijosDTO tuhi : tutoresHijosDAO.getTutores_hijos()){
            if (tuhi.getId_padre() == id){
                idsHijos.add(tuhi.getId_hijo());
            }
        }
        for (HijosDTO hijo : hijosDAO.obtenerHijos()){
            if (idsHijos.contains(hijo.getId())){
                hijos.add(hijo);
            }
        }
        return hijos;
    }

}
