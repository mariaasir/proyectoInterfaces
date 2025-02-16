package org.example.proyectointerfaces.VentanaTutoresLegales;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import org.example.proyectointerfaces.Hijos.HijosDTO;
import org.example.proyectointerfaces.Sincronizacion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de tutores legales.
 */
public class VentanaTutoresController {
    Sincronizacion sincronizacion;
    TutoresLegalesDTO usuario;

    @FXML
    private Label labelDatosTutor, labelHijos, labelSaludo, textPadres, textHijos;

    @FXML
    private MenuButton idiomas;

    //Crea 3 items para el menuButton
    MenuItem español = new MenuItem("Español");
    MenuItem ingles = new MenuItem("Ingles");
    MenuItem frances = new MenuItem("Frances");

    //Crea el resource para establecer el idioma por defecto
    ResourceBundle bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));

    /**
     * Inicializa el controlador, añadiendo los eventos para cambiar el idioma.
     */
    @FXML
    public void initialize() {
        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));
        ingles.setOnAction(e -> cambiarIdioma("Ingles"));
        frances.setOnAction(e -> cambiarIdioma("Frances"));
    }

    /**
     * Carga los datos del tutor y sus hijos en la ventana.
     *
     * @param user           Nombre de usuario del tutor.
     * @param sincronizacion Objeto de sincronización para obtener los datos.
     */
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

    /**
     * Actualiza los textos de la ventana según el idioma seleccionado.
     */
    private void actualizarIdioma() {
        textPadres.setText(bundle.getString("ventanaPadres.textPadres"));
        textHijos.setText(bundle.getString("ventanaPadres.textHijos"));
        labelSaludo.setText(bundle.getString("ventanaPadres.saludo") + " " + usuario.getNombre() + " " + usuario.getApellido());
        labelDatosTutor.setText(
                bundle.getString("ventanaPadres.Dni") + " " + usuario.getDni() +
                        "\n" + bundle.getString("ventanaPadres.fechaNacimiento") + " " + usuario.getFechaNacimiento() +
                        "\n" + bundle.getString("ventanaPadres.Email") + " " + usuario.getEmail() +
                        "\n" + bundle.getString("ventanaPadres.Direccion") + " " + usuario.getDireccion() + " " + usuario.getCP() +
                        "\n" + bundle.getString("ventanaPadres.telefono") + " " + usuario.getTelefono()
        );
        List<HijosDTO> hijosList = sincronizacion.devolverHijosDeUnPadre(usuario.getId());
        List<String> salida = new ArrayList<>();

        for (HijosDTO hijosDTO : hijosList) {
            String infoHijo = bundle.getString("hijos.nombre") + " " + hijosDTO.getNombre() + " " + hijosDTO.getApellidos() +
                    "\n" + bundle.getString("hijos.fechaNacimiento") + " " + hijosDTO.getFecha_Nacimiento() +
                    "\n" + bundle.getString("hijos.direccion") + hijosDTO.getDireccion() + " " + hijosDTO.getCodigo_Postal() +
                    "\n" + bundle.getString("hijos.telefonoEmergencia") + " " + hijosDTO.getTelefono_Emergencia() +
                    "\n" + bundle.getString("hijos.seccion") + " " + hijosDTO.getSeccion_Nombre();

            if (hijosDTO.getdNI_NIE() != null) {
                infoHijo += "\n" + bundle.getString("hijos.dni_nie") + " " + hijosDTO.getdNI_NIE();
            }
            salida.add(infoHijo);
        }
        labelHijos.setText(String.join("\n\n\n", salida));
    }

    /**
     * Cambia el idioma de la interfaz según la selección del usuario.
     *
     * @param idiomaSeleccionado El idioma seleccionado por el usuario.
     */
    private void cambiarIdioma(String idiomaSeleccionado) {
        if ("Español".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));
            idiomas.setText("Español");
        } else if ("Ingles".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("en", "EN"));
            idiomas.setText("Ingles");
        } else if ("Frances".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("fr", "FR"));
            idiomas.setText("Frances");
        }
        actualizarIdioma();
    }
}
