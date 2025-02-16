package org.example.proyectointerfaces.SelectorInformes;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controlador para la selección de informes en la aplicación.
 * Permite al usuario elegir un tipo de informe y un idioma.
 */
public class SelectorInformesController {
    @FXML
    private MenuButton tipoInforme;

    @FXML
    private MenuButton idiomas;

    @FXML
    private Button botonConfirmar;

    @FXML
    private Button botonAyuda;

    @FXML
    private Text titulo;

    @FXML
    private ImageView logo;

    // Creación de los elementos del menú para los informes
    private MenuItem informeX = new MenuItem("Informe de Eventos Pasados");
    private MenuItem informeY = new MenuItem("Informe de Eventos Futuros");
    private MenuItem informeA = new MenuItem("Informe General");
    private MenuItem informeZ = new MenuItem("Informe de Secciones");

    // Crear 3 items para el menuButton de idiomas
    MenuItem español = new MenuItem("Español");
    MenuItem ingles = new MenuItem("Ingles");
    MenuItem frances = new MenuItem("Frances");

    // ResourceBundle para el idioma por defecto
    ResourceBundle bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));

    /**
     * Inicializa la interfaz de usuario, agregando opciones de informes e idiomas.
     */
    @FXML
    public void initialize() {
        // Agregar los informes al MenuButton
        tipoInforme.getItems().addAll(informeA, informeX, informeY, informeZ);

        // Agregar eventos a cada informe para actualizar el texto del MenuButton
        informeX.setOnAction(event -> tipoInforme.setText(bundle.getString("informes.pasados")));
        informeY.setOnAction(event -> tipoInforme.setText(bundle.getString("informes.futuros")));
        informeA.setOnAction(event -> tipoInforme.setText(bundle.getString("informes.general")));
        informeZ.setOnAction(event -> tipoInforme.setText(bundle.getString("informes.secciones")));

        // Añadir idiomas al menú
        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));
        ingles.setOnAction(e -> cambiarIdioma("Ingles"));
        frances.setOnAction(e -> cambiarIdioma("Frances"));
    }

    /**
     * Confirma la selección del informe y carga la pantalla correspondiente.
     *
     * @param event Evento de acción del botón confirmar.
     */
    @FXML
    private void confirmarSeleccion(ActionEvent event) {
        String selectedInforme = tipoInforme.getText();

        String informeEventosPasados = bundle.getString("informes.pasados");
        String informeEventosFuturos = bundle.getString("informes.futuros");
        String informeGeneral = bundle.getString("informes.general");
        String informeSecciones = bundle.getString("informes.secciones");

        // Comparar con las cadenas del ResourceBundle en lugar de las cadenas fijas
        if (informeEventosPasados.equals(selectedInforme)) {
            cargarPantalla("/org/example/proyectointerfaces/informeEventosPasados.fxml");
        } else if (informeEventosFuturos.equals(selectedInforme)) {
            cargarPantalla("/org/example/proyectointerfaces/informesEventosFuturos.fxml");
        } else if (informeGeneral.equals(selectedInforme)) {
            cargarPantalla("/org/example/proyectointerfaces/informeGeneral.fxml");
        } else if (informeSecciones.equals(selectedInforme)) {
            cargarPantalla("/org/example/proyectointerfaces/informeSecciones.fxml");
        } else {
            mostrarAlerta();
        }
    }

    /**
     * Actualiza los textos de la interfaz según el idioma seleccionado.
     */
    private void actualizarIdioma() {
        // Actualiza los textos de la interfaz de usuario con los textos del ResourceBundle
        titulo.setText(bundle.getString("informes.titulo"));
        tipoInforme.setText(bundle.getString("informes.tipoInforme"));
        botonConfirmar.setText(bundle.getString("informes.botonConfirmar"));
        informeX.setText(bundle.getString("informes.pasados"));
        informeY.setText(bundle.getString("informes.futuros"));
        informeA.setText(bundle.getString("informes.general"));
        informeZ.setText(bundle.getString("informes.secciones"));
    }

    /**
     * Cambia el idioma de la interfaz de usuario.
     *
     * @param idiomaSeleccionado Nombre del idioma seleccionado.
     */
    private void cambiarIdioma(String idiomaSeleccionado) {
        // Cambia el idioma y actualiza la interfaz
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

    /**
     * Muestra una alerta si el usuario no ha seleccionado un informe.
     */
    private void mostrarAlerta() {
        // Muestra una alerta si no se ha seleccionado un informe
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Selección requerida");
        alerta.setHeaderText(null);
        alerta.setContentText("Por favor, selecciona un informe antes de continuar.");
        alerta.showAndWait();
    }

    /**
     * Carga una nueva pantalla según el informe seleccionado.
     *
     * @param rutaFXML Ruta del archivo FXML a cargar.
     */
    private void cargarPantalla(String rutaFXML) {
        // Carga la pantalla de acuerdo al informe seleccionado
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) tipoInforme.getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla: " + rutaFXML);
        }
    }

    /**
     * Metodo que abre la ventana de ayuda.
     */
    @FXML
    private void abrirAyuda() {
        Stage ayudaStage = new Stage();
        ayudaStage.setTitle("Ayuda");

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Cargar el HTML desde los recursos del proyecto
        String url = getClass().getResource("/org/example/proyectointerfaces/index.html").toExternalForm();
        webEngine.load(url);

        Scene scene = new Scene(webView, 600, 400);
        ayudaStage.setScene(scene);
        ayudaStage.initModality(Modality.APPLICATION_MODAL);
        ayudaStage.show();
    }
}
