package org.example.proyectointerfaces.Informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InformeXController {

    @FXML
    private TextField fechaField;

    @FXML
    private TextField idPacienteField;

    @FXML
    private Button volverButton;

    @FXML
    private void generarInforme() {
        String fecha = fechaField.getText();
        String idPaciente = idPacienteField.getText();

        if (fecha.isEmpty() || idPaciente.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        System.out.println("Generando informe X con Fecha: " + fecha + " y ID Paciente: " + idPaciente);
    }

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
