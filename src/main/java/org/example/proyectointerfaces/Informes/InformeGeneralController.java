package org.example.proyectointerfaces.Informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.*;

/**
 * Controlador para la generación de informe general.
 */
public class InformeGeneralController {
    @FXML
    private Button generarInformeButton, visualizarInforme, volverButton;

    @FXML
    private Text titulo, informacion;

    @FXML
    private MenuButton idiomas;

    String ruta;

    // Crear 3 items para el menuButton
    javafx.scene.control.MenuItem español = new javafx.scene.control.MenuItem("Español");
    javafx.scene.control.MenuItem ingles = new javafx.scene.control.MenuItem("Ingles");
    javafx.scene.control.MenuItem frances = new javafx.scene.control.MenuItem("Frances");

    // Crear el resource para establecer el idioma por defecto
    ResourceBundle bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));

    /**
     * Metodo de inicialización del controlador.
     * Se encarga de llenar el ComboBox con las secciones disponibles.
     */
    @FXML
    public void initialize() {
        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));
        ingles.setOnAction(e -> cambiarIdioma("Ingles"));
        frances.setOnAction(e -> cambiarIdioma("Frances"));
    }

    /**
     * Genera un informe de eventos futuros basado en la sección seleccionada.
     * El informe se genera utilizando JasperReports y se guarda como un archivo PDF.
     */
    public void generarInforme() {
        try {
            // Conexión a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/orion", "root", "");

            // Generar el informe sin parámetros adicionales, usando la consulta definida en el reporte
            JasperPrint print = JasperFillManager.fillReport("src/main/resources/org/example/proyectointerfaces/Jaspers/ReportOrionGeneral.jasper", null, connection);

            // Mostrar el diálogo para guardar el archivo
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Informe");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("hijos_informe.pdf");

            Stage stage = (Stage) generarInformeButton.getScene().getWindow();
            stage.setResizable(false);
            File file = fileChooser.showSaveDialog(stage);

            // Si el usuario selecciona una ubicación para guardar
            if (file != null) {
                // Exportar el informe a un archivo PDF
                JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath());
                System.out.println("¡Informe generado exitosamente en: " + file.getAbsolutePath() + "!");
            } else {
                System.out.println("Guardado cancelado por el usuario.");
            }

            ruta = file.getAbsolutePath();
            // Mostrar una alerta de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informe Generado");
            alert.setHeaderText(null);
            alert.setContentText("El informe se ha generado correctamente.");
            alert.showAndWait();

        } catch (Throwable e) {
            e.printStackTrace();
            // Mostrar una alerta de error si algo falla
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Hubo un error al generar el informe: " + e.getMessage());
            alert.showAndWait();
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

            Stage stageActual = (Stage) volverButton.getScene().getWindow();
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
     * Metodo para actualizar el idioma en los componentes de la interfaz
     */
    private void actualizarIdioma() {
        // Actualiza los idiomas según los campos establecidos en los Resources
        titulo.setText(bundle.getString("informesGeneral.titulo"));
        informacion.setText(bundle.getString("informesGeneral.informacion"));
        generarInformeButton.setText(bundle.getString("informes.generarInforme"));
        visualizarInforme.setText(bundle.getString("informes.visualizarInforme"));
        volverButton.setText(bundle.getString("informes.volver"));
    }

    /**
     * Metodo para detectar y cambia el idioma de la interfaz cuando el usuario interactue con el combobox
     *
     * @param idiomaSeleccionado es el texto que tiene el combobox para saber a que idioma se cambia.
     */
    private void cambiarIdioma(String idiomaSeleccionado) {
        if ("Español".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));
            idiomas.setText("Español");
            titulo.setLayoutX(70);
        } else if ("Ingles".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("en", "EN"));
            idiomas.setText("Ingles");
            titulo.setLayoutX(95);
        } else if ("Frances".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("fr", "FR"));
            idiomas.setText("Frances");
            titulo.setLayoutX(70);
            generarInformeButton.setLayoutX(122);
        }
        actualizarIdioma();
    }
}

