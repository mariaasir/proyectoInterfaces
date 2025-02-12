package org.example.proyectointerfaces.Informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.proyectointerfaces.Conexion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;

public class InformeEventosFuturosController {

    @FXML
    private DatePicker fechaFuturoDatePicker;

    @FXML
    private Button generarInformeButton;

    @FXML
    private Button volverButton;

    // Formato para la fecha
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    public void initialize() {
        // Restringir el DatePicker para que solo permita fechas futuras
        fechaFuturoDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now().plusDays(1))) { // Solo fechas futuras
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Color visual para deshabilitados
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void generarInforme() {
        generarInformeButton.setDisable(true); // Evitar múltiples clics

        LocalDate fechaSeleccionada = fechaFuturoDatePicker.getValue();

        if (fechaSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una fecha antes de generar el informe.");
            generarInformeButton.setDisable(false);
            return;
        }

        // Convertir la fecha seleccionada en formato yyyy-MM-dd
        String fechaSeleccionadaStr = fechaSeleccionada.format(formatter);

        System.out.println("Generando informe para la fecha: " + fechaSeleccionadaStr);

        JasperPrint jasperPrint = null;

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                mostrarAlerta("Error", "No se pudo establecer conexión con la base de datos.");
                generarInformeButton.setDisable(false);
                return;
            }

            // Parámetros para JasperReports
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("FechaPasada", fechaSeleccionadaStr);

            // Cargar el archivo del informe
            InputStream reportStream = getClass().getResourceAsStream("/org/example/proyectointerfaces/Jaspers/ReporteEvento.jasper");
            if (reportStream == null) {
                mostrarAlerta("Error", "No se encontró el archivo del informe.");
                generarInformeButton.setDisable(false);
                return;
            }

            // Generar el informe
            jasperPrint = JasperFillManager.fillReport(reportStream, parametros, con);

            if (jasperPrint == null) {
                mostrarAlerta("Error", "No se pudo generar el informe.");
                generarInformeButton.setDisable(false);
                return;
            }

            // Usar FileChooser para seleccionar la ubicación de guardado
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Informe");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("evento_informe.pdf");

            Stage stage = (Stage) generarInformeButton.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
                System.out.println("¡Informe generado exitosamente en: " + file.getAbsolutePath() + "!");
            } else {
                System.out.println("Guardado cancelado por el usuario.");
            }

        } catch (JRException e) {
            mostrarAlerta("Error de JasperReports", "No se pudo generar el informe.");
            e.printStackTrace();
        } catch (Exception e) {
            mostrarAlerta("Error inesperado", "Ocurrió un error inesperado.");
            e.printStackTrace();
        } finally {
            generarInformeButton.setDisable(false);
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
