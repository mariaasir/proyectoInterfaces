package org.example.proyectointerfaces.VentanaTutoresLegales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.proyectointerfaces.InicioSesion.InicioSesionApplication;

import java.io.IOException;

/**
 * Aplicación principal para mostrar la ventana de la cuenta de tutor.
 */
public class VentanaTutoresApplication extends Application {
    /**
     * Metodo que inicializa y muestra la ventana de la cuenta de tutor.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InicioSesionApplication.class.getResource("/org/example/proyectointerfaces/ventanaTutores.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 629);
        stage.setTitle("Cuenta de tutor");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metodo principal para lanzar la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }

}
