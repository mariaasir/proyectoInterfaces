package org.example.proyectointerfaces.Informes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InformeXController {

    @FXML
    private TextField fechaField;

    @FXML
    private TextField idPacienteField;


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
}
