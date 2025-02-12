package org.example.proyectointerfaces.Informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class InformeSeccionController {

    @FXML
    private ComboBox<String> seccionComboBox;

    @FXML
    private Button generarInformeButton;

    @FXML
    private Button volverButton;

    @FXML
    private void generarInforme() {
        String seleccionada = seccionComboBox.getValue();

        if (seleccionada == null) {
            System.out.println("Por favor, selecciona una sección.");
            return;
        }
        System.out.println("Generando informe para la sección: " + seleccionada);
    }

    /*
    // Conexión con la base de datos
    Class.forName("org.mariadb.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital", "giftiliano", "giftilian");

    // Parámetros para JasperReports (cambiar cuando termine reportes)
    Map<String, Object> parametros = new HashMap<>();
    parametros.put("idPaciente", patientId);  // Cambiado el nombre del parámetro a "idPaciente"

    // Cargar el archivo del informe desde el classpath
    String reportPath = getClass().getResource("/informes/HistorialPacientes.jasper").getPath();
    if (reportPath == null) {
        throw new IOException("No se encontró el archivo HistorialPacientes.jasper");
    }

    // Generar el informe con la conexión a la base de datos
    JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametros, con);

    // Guardar el informe en un archivo PDF
    JasperExportManager.exportReportToPdfFile(jasperPrint, "historial_medico.pdf");

    // Mensaje de confirmación
    System.out.println("¡Informe del historial médico generado exitosamente!");
} catch (Exception e) {
    e.printStackTrace();
    System.out.println("Error al generar el informe del historial médico.");
}

System.out.println("Generando informe Z con Doctor: " + doctor + " y Departamento: " + departamento);
}
*/
    @FXML
    private void volverASelector(ActionEvent event) {
        try {
            // Cargar la pantalla del selector de informes
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/menuInformes.fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual y cerrarla
            Stage stageActual = (Stage) volverButton.getScene().getWindow();
            stageActual.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al volver a la pantalla de selección de informes.");
        }
    }

}



