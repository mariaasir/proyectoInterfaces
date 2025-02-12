package org.example.proyectointerfaces.Informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import net.sf.jasperreports.engine.*;

public class InformeGeneralController {

    @FXML
    private Button generarInformeButton;

    @FXML
    private Button volverButton;

    String ruta;

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

    @FXML
    private void volverASelector(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/menuInformes.fxml"));
            Parent root = loader.load();

            Stage stageActual = (Stage) volverButton.getScene().getWindow();
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
}
