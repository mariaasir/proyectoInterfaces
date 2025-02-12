package org.example.proyectointerfaces.InicioSesion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que se encarga de lanzar la ventana de inicio de sesión.
 */
public class InicioSesionApplication extends Application {
    /**
     * Metodo principal de la aplicación que se ejecuta al iniciar la aplicación.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si hay un error al cargar el archivo FXML de la pantalla de inicio de sesión.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InicioSesionApplication.class.getResource("/org/example/proyectointerfaces/inicioSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 629);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Metodo main que lanza la aplicación.
     * Este metodo es el punto de entrada de la aplicación.
     *
     * @param args Los argumentos que se pasan a la aplicación al ejecutarla .
     */
    public static void main(String[] args) {
        launch();
    }
}