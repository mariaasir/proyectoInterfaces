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
import javafx.stage.Stage;

import java.io.IOException;

public class SelectorInformesController {

    @FXML
    private MenuButton tipoInforme;

    @FXML
    private MenuButton idiomas;

    @FXML
    private Button botonConfirmar;

  //  private String informeSeleccionado = null;


    @FXML
    private Text textoInformes;

    @FXML
    private ImageView logo;

    // Creación de los elementos del menú
    private MenuItem informeX = new MenuItem("Informe de X");
    private MenuItem informeY = new MenuItem("Informe de Y");
    private MenuItem informeZ = new MenuItem("Informe de Secciones");

    private MenuItem espaniol = new MenuItem("Español");
    private MenuItem ingles = new MenuItem("Inglés");

    @FXML
    public void initialize() {
        // Agregar los informes al MenuButton
        tipoInforme.getItems().addAll(informeX, informeY, informeZ);

        // Agregar eventos a cada informe para actualizar el texto del MenuButton
        informeX.setOnAction(event -> tipoInforme.setText("Informe de X"));
        informeY.setOnAction(event -> tipoInforme.setText("Informe de Y"));
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

          if("Informe de X".equals(selectedInforme)) {
              cargarPantalla("/org/example/proyectointerfaces/informeX.fxml");
          } else if("Informe de Y".equals(selectedInforme)) {
              cargarPantalla("/org/example/proyectointerfaces/informeY.fxml");
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


}
