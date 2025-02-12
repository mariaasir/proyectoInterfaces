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
}
