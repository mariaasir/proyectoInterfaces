package org.example.proyectointerfaces.SelectorInformes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Text textoInformes;

    @FXML
    private ImageView logo;

    // Creación de los elementos del menú
    private MenuItem informeX = new MenuItem("Informe de Eventos Pasados");
    private MenuItem informeY = new MenuItem("Informe de Eventos Futuros");
    private MenuItem informeA = new MenuItem("Informe General");
    private MenuItem informeZ = new MenuItem("Informe de Secciones");

    private MenuItem espaniol = new MenuItem("Español");
    private MenuItem ingles = new MenuItem("Inglés");

    @FXML
    public void initialize() {
        // Agregar los informes al MenuButton
        tipoInforme.getItems().addAll(informeA, informeX, informeY, informeZ);

        // Agregar eventos a cada informe para actualizar el texto del MenuButton
        informeX.setOnAction(event -> tipoInforme.setText("Informe de Eventos Pasados"));
        informeY.setOnAction(event -> tipoInforme.setText("Informe de Eventos Futuros"));
        informeA.setOnAction(event -> tipoInforme.setText("Informe General"));
        informeZ.setOnAction(event -> tipoInforme.setText("Informe de Secciones"));

        //Agregar los idiomas al MenuButton
         idiomas.getItems().addAll(espaniol, ingles);

        // Eventos para cambiar idioma
        espaniol.setOnAction(event -> cambiarIdioma("es"));
        ingles.setOnAction(event -> cambiarIdioma("en"));
    }

    @FXML
        private void confirmarSeleccion(ActionEvent event) {
            String selectedInforme = tipoInforme.getText();

          if("Informe de Eventos Pasados".equals(selectedInforme)) {
              cargarPantalla("/org/example/proyectointerfaces/informeEventosPasados.fxml");
          } else if("Informe de Eventos Futuros".equals(selectedInforme)) {
              cargarPantalla("/org/example/proyectointerfaces/informesEventosFuturos.fxml");
          }else if("Informe General".equals(selectedInforme)) {
              cargarPantalla("/org/example/proyectointerfaces/informeGeneral.fxml");
          }else if("Informe de Secciones".equals(selectedInforme)) {
              cargarPantalla("/org/example/proyectointerfaces/informeSecciones.fxml");
          }else {
              mostrarAlerta();
          }
        }

    private void cambiarIdioma(String idioma) {
        switch (idioma) {
            case "es":
                textoInformes.setText("INFORME");
                tipoInforme.setText("Seleccionar Informe");
                botonConfirmar.setText("Confirmar");
                System.out.println("Idioma cambiado a Español");
                break;
            case "en":
                textoInformes.setText("REPORT");
                tipoInforme.setText("Select Report");
                botonConfirmar.setText("Confirm");
                System.out.println("Language changed to English");
                break;
        }
    }
    private void mostrarAlerta() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Selección requerida");
        alerta.setHeaderText(null);
        alerta.setContentText("Por favor, selecciona un informe antes de continuar.");
        alerta.showAndWait();
    }

    private void cargarPantalla(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) tipoInforme.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla: " + rutaFXML);
        }
    }

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
