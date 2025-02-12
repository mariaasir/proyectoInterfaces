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
        labelSaludo.setText("Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
        labelDatosTutor.setText(" DNI / NIE: " + usuario.getDni() +
                "\n Fecha de Nacimiento: " + usuario.getFechaNacimiento() +
                "\n Correo Electrónico: " + usuario.getEmail() +
                "\n Dirección: " + usuario.getDireccion() + " " + usuario.getCP() +
                "\n Teléfono: " + usuario.getTelefono());


        List<HijosDTO> hijosList = sincronizacion.devolverHijosDeUnPadre(usuario.getId());
        List<String> salida = new ArrayList<>();
        for (HijosDTO hijosDTO : hijosList) {
            if (hijosDTO.getdNI_NIE() == null) {
                salida.add(
                        (" Nombre: " + hijosDTO.getNombre() + " " + hijosDTO.getApellidos()) +
                                "\n Fecha de Nacimiento: " + hijosDTO.getFecha_Nacimiento() +
                                "\n Dirección: " + hijosDTO.getDireccion() + " " + hijosDTO.getCodigo_Postal() +
                                "\n Teléfono de Emergencia" + hijosDTO.getTelefono_Emergencia() +
                                "\n Sección: " + hijosDTO.getSeccion_Nombre());
            } else {
                salida.add(
                        (" Nombre: " + hijosDTO.getNombre() + " " + hijosDTO.getApellidos()) +
                                "\n DNI / NIE: " + hijosDTO.getdNI_NIE() +
                                "\n Fecha de Nacimiento: " + hijosDTO.getFecha_Nacimiento() +
                                "\n Dirección: " + hijosDTO.getDireccion() + " " + hijosDTO.getCodigo_Postal() +
                                "\n Teléfono de Emergencia" + hijosDTO.getTelefono_Emergencia() +
                                "\n Sección: " + hijosDTO.getSeccion_Nombre());
            }
        }

        labelHijos.setText(String.join("\n\n\n", salida));
    }
}
