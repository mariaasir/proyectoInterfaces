package org.example.proyectointerfaces.Informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.awt.*;
import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;
import java.time.Instant;

import net.sf.jasperreports.engine.*;

/**
 * Controlador para la generación de informes de eventos futuros en la aplicación.
 */
public class InformeEventosFuturosController {
    @FXML
    private Button generarInformeButton, visualizarInformeButton;

    @FXML
    private Button volverButton;

    @FXML
    private MenuButton idiomas;

    @FXML
    private Text titulo , informacion;

    @FXML
    private ComboBox<String> seccionComboBox; // Sección seleccionada

    String ruta;


    //Crea 3 items para el menuButton
    MenuItem español = new MenuItem("Español");
    MenuItem ingles = new MenuItem("Ingles");
    MenuItem frances = new MenuItem("Frances");

    //Crea el resource para establecer el idioma por defecto
    ResourceBundle bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));

    /**
     * Metodo de inicialización del controlador.
     * Se encarga de llenar el ComboBox con las secciones disponibles.
     */
    @FXML
    public void initialize() {
        seccionComboBox.getItems().addAll(
                "Tribu", "Mambos", "Rhygings"
        );

        // Añadir idiomas al menú
        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));

        ingles.setOnAction(e -> cambiarIdioma("Ingles"));

        frances.setOnAction(e -> cambiarIdioma("Frances"));

    }

    /**
     * Genera un informe de eventos futuros basado en la sección seleccionada.
     * El informe se genera utilizando JasperReports y se guarda como un archivo PDF.
     */
    @FXML
    private void generarInforme() {
        Date fecha = Date.from(Instant.now());
        String seccionSeleccionada = seccionComboBox.getValue();

        if (seccionSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una sección antes de generar el informe.");
            generarInformeButton.setDisable(false);
            return;
        }

        // Parámetros para JasperReports
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("FechaPasada", fecha); // Usar java.sql.Date
        parametros.put("Seccion", seccionSeleccionada);

        try {
            // Cargar el driver JDBC y establecer la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/orion", "root", "");

            if (con == null) {
                mostrarAlerta("Error", "No se pudo establecer conexión con la base de datos.");
                generarInformeButton.setDisable(false);
                return;
            }

            // Cargar el informe con los parámetros y la conexión
            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/org/example/proyectointerfaces/Jaspers/ReportOrionEventosFuturos.jasper", parametros, con);

            // Mostrar el diálogo para guardar el archivo
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Informe");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("informe_eventos_futuros.pdf");

            Stage stage = (Stage) generarInformeButton.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);
            stage.setResizable(false);

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

    /**
     * Regresa a la pantalla de selección de informes.
     *
     * @param event Evento de acción que desencadena el cambio de pantalla.
     */
    @FXML
    private void volverASelector(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/menuInformes.fxml"));
            Parent root = loader.load();

            Stage stageActual = (Stage) seccionComboBox.getScene().getWindow();
            stageActual.setResizable(false);
            stageActual.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al volver a la pantalla de selección de informes.");
        }
    }

    /**
     * Abre el informe generado en la aplicación predeterminada del sistema.
     */
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

    /**
     * Muestra una alerta informativa en la interfaz de usuario.
     *
     * @param titulo  Título de la alerta.
     * @param mensaje Mensaje a mostrar en la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void actualizarIdioma() {

        //Actualiza los idiomas según los campos establecidos en los Resources
        titulo.setText(bundle.getString("informesFuturos.titulo"));
        informacion.setText(bundle.getString("informesFuturos.informacion"));
        generarInformeButton.setText(bundle.getString("informes.generarInforme"));
        visualizarInformeButton.setText(bundle.getString("informes.visualizarInforme"));
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
            titulo.setLayoutX(95);
            informacion.setLayoutX(75);

        } else if ("Frances".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("fr", "FR"));
            idiomas.setText("Frances");
            titulo.setLayoutX(50);
            informacion.setLayoutX(75);
            generarInformeButton.setLayoutX(115);


        }
        actualizarIdioma();
    }
}
