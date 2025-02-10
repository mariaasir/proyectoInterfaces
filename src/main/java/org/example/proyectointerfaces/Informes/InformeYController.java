package org.example.proyectointerfaces.Informes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InformeYController {

    @FXML
    private TextField especialidadField;

    @FXML
    private TextField hospitalField;


    @FXML
    private void generarInforme() {
        String especialidad = especialidadField.getText();
        String hospital = hospitalField.getText();

        if (especialidad.isEmpty() || hospital.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        System.out.println("Generando informe Y con Especialidad: " + especialidad + " y Hospital: " + hospital);
    }
}
