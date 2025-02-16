package org.example.proyectointerfaces.Informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class InformeSeccionController {

    @FXML
    private ComboBox<String> seccionComboBox;

    @FXML
    private Button generarInformeButton, visualizarInforme, volverButton;


    @FXML
    private MenuButton idiomas;

    @FXML
    private Label informacion;

    @FXML
    private Text titulo;

    private String ruta;

    //Crea 3 items para el menuButton
    javafx.scene.control.MenuItem español = new javafx.scene.control.MenuItem("Español");
    javafx.scene.control.MenuItem ingles = new javafx.scene.control.MenuItem("Ingles");
    javafx.scene.control.MenuItem frances = new MenuItem("Frances");

    //Crea el resource para establecer el idioma por defecto
    ResourceBundle bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));


    @FXML
    public void initialize() {
        // Agregar las secciones al ComboBox
        seccionComboBox.getItems().addAll(
                "Tribu", "Mambos", "Rhygings"
        );


        // Añadir idiomas al menú
        idiomas.getItems().addAll(español, ingles, frances);


        español.setOnAction(e -> cambiarIdioma("Español"));

        ingles.setOnAction(e -> cambiarIdioma("Ingles"));

        frances.setOnAction(e -> cambiarIdioma("Frances"));

    }

    public void generarInforme() {
        String seccionSeleccionada = seccionComboBox.getSelectionModel().getSelectedItem();


        // Validar si se ha seleccionado una sección y fecha
        if (seccionSeleccionada == null || seccionSeleccionada.isEmpty()) {
            mostrarAlerta("Complete los campos", "Debe seleccionar una sección para poder generar el informe");
            return;
        }

        // Pasar parámetros al informe
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("Seccion", seccionSeleccionada);

        try {
            // Cargar el driver JDBC y establecer la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/orion", "root", "");

            // Cargar el informe con los parámetros y la conexión
            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/org/example/proyectointerfaces/Jaspers/ReporteOrionHijosSeccion.jasper", parametro, connection);

            // Mostrar el diálogo para guardar el archivo
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Informe");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("informe_secciones.pdf");

            Stage stage = (Stage) generarInformeButton.getScene().getWindow();
            stage.setResizable(false);
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                // Exportar el informe a un archivo PDF
                JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
                System.out.println("¡Informe generado exitosamente en: " + file.getAbsolutePath() + "!");
            } else {
                System.out.println("Guardado cancelado por el usuario.");
            }

            ruta = file.getAbsolutePath();
            mostrarAlerta("Informe Generado", "El informe se ha generado correctamente.");

        } catch (Throwable e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Hubo un error al generar el informe: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void volverASelector(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/menuInformes.fxml"));
            Parent root = loader.load();

            Stage stageActual = (Stage) volverButton.getScene().getWindow();
            stageActual.setResizable(false);
            stageActual.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al volver a la pantalla de selección de informes.");
        }
    }

    public void visualizarInforme() {
        // Usamos la variable ruta que contiene la ruta del archivo generado
        if (ruta == null || ruta.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se ha generado el informe. Asegúrese de haberlo guardado correctamente.");
            alert.showAndWait();
            return;
        }

        // Crear el archivo PDF desde la ruta
        File archivoPDF = new File(ruta);

        // Comprobar si el archivo existe
        if (archivoPDF.exists()) {
            try {
                // Abre el archivo PDF usando la aplicación predeterminada del sistema
                Desktop desktop = Desktop.getDesktop();
                desktop.open(archivoPDF);
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al abrir el PDF");
                alert.setHeaderText(null);
                alert.setContentText("Hubo un problema al abrir el informe: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            // Si el archivo no existe, muestra un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Archivo no encontrado");
            alert.setHeaderText(null);
            alert.setContentText("El archivo PDF no se encuentra en la ubicación especificada.");
            alert.showAndWait();
        }
    }

    private void actualizarIdioma() {

        //Actualiza los idiomas según los campos establecidos en los Resources
        titulo.setText(bundle.getString("informesSecciones.titulo"));
        informacion.setText(bundle.getString("informesSecciones.informacion"));
        generarInformeButton.setText(bundle.getString("informes.generarInforme"));
        visualizarInforme.setText(bundle.getString("informes.visualizarInforme"));
        volverButton.setText(bundle.getString("informes.volver"));
        seccionComboBox.setPromptText(bundle.getString("informesPasados.comboBox"));


    }

    private void cambiarIdioma(String idiomaSeleccionado) {
        if ("Español".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));
            idiomas.setText("Español");
            titulo.setLayoutX(70);
            informacion.setLayoutX(80);

        } else if ("Ingles".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("en", "EN"));
            idiomas.setText("Ingles");
            titulo.setLayoutX(125);
            informacion.setLayoutX(45);

        } else if ("Frances".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("fr", "FR"));
            idiomas.setText("Frances");
            titulo.setLayoutX(100);
            informacion.setLayoutX(40);
            generarInformeButton.setLayoutX(120);


        }
        actualizarIdioma();
    }

}



