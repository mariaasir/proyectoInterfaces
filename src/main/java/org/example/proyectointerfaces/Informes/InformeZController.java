package org.example.proyectointerfaces.Informes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class InformeZController {

    @FXML
    private TextField doctorField;

    @FXML
    private TextField departamentoField;


    @FXML
    private void generarInforme() {
        //try {


        //CAMBIAR
        String doctor = doctorField.getText();
        String departamento = departamentoField.getText();

        if (doctor.isEmpty() || departamento.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
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
    }
}

