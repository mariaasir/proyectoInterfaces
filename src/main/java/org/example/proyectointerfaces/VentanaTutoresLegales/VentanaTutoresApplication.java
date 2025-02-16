package org.example.proyectointerfaces.VentanaTutoresLegales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.proyectointerfaces.InicioSesion.InicioSesionApplication;

import java.io.IOException;

public class VentanaTutoresApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InicioSesionApplication.class.getResource("/org/example/proyectointerfaces/ventanaTutores.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 629);
        stage.setTitle("Cuenta de tutor");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
