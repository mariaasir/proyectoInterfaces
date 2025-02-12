package org.example.proyectointerfaces.VentanaTutoresLegales;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.proyectointerfaces.Hijos.HijosDTO;
import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Sincronizacion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.util.ArrayList;
import java.util.List;


public class VentanaTutoresController {


    Sincronizacion sincronizacion;
    TutoresLegalesDTO usuario;

    @FXML
    private Label labelSaludo;

    @FXML
    private Label labelDatosTutor;

    @FXML
    private Label labelHijos;

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
                "\n" + usuario.getTelefono() +
                "\n" + usuario.getFechaNacimiento());
        List<HijosDTO> hijosList = sincronizacion.devolverHijosDeUnPadre(usuario.getId());
        List<String> salida = new ArrayList<>();
        for (HijosDTO hijosDTO : hijosList) {
            if (hijosDTO.getdNI_NIE() == null) {
                salida.add((hijosDTO.getNombre() + " " + hijosDTO.getApellidos()).toUpperCase() +
                        "\n" + hijosDTO.getDireccion() + " " + hijosDTO.getCodigo_Postal() +
                        "\n" + hijosDTO.getFecha_Nacimiento() +
                        "\n" + hijosDTO.getSeccion_Nombre());
            } else {
                salida.add((hijosDTO.getNombre() + " " + hijosDTO.getApellidos()).toUpperCase() +
                        "\n" + hijosDTO.getdNI_NIE() +
                        "\n" + hijosDTO.getDireccion() + " " + hijosDTO.getCodigo_Postal() +
                        "\n" + hijosDTO.getFecha_Nacimiento() +
                        "\n" + hijosDTO.getSeccion_Nombre());
            }
        }

        labelHijos.setText(String.join("\n\n\n", salida));
    }
}
