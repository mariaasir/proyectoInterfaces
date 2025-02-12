package org.example.proyectointerfaces.VentanaTutoresLegales;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Sincronizacion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;


public class VentanaTutoresController {


    Sincronizacion sincronizacion;
    TutoresLegalesDTO usuario;

    @FXML
    private Label labelSaludo;

    @FXML
    private Label labelDatosTutor;

    @FXML
    public void initialize() {

    }

    @FXML
    public void cargarVentana(String user, Sincronizacion sincronizacion) {
        this.sincronizacion = sincronizacion;
        usuario = sincronizacion.dameUnTutor(user);
        labelSaludo.setText("Buenas " + usuario.getNombre() + " " + usuario.getApellido());
        labelDatosTutor.setText(usuario.getEmail() +
                "\n" + usuario.getDireccion() + " " + usuario.getCP() +
                "\n" + usuario.getTelefono());
    }
}
