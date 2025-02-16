package org.example.proyectointerfaces.Registro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que gestiona la ventana de registro.
 * Extiende de Application para lanzar la interfaz gráfica.
 */
public class RegistroApplication extends Application {
    /**
     * Metodo start que se ejecuta al iniciar la aplicación.
     * Carga la interfaz registro.fxml y la muestra en una ventana.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si no se puede cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroApplication.class.getResource("/org/example/proyectointerfaces/registro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 646);
        stage.setTitle("Registro");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metodo principal que inicia la aplicación.
     *
     * @param args Argumentos pasados a la aplicación.
     */
    public static void main(String[] args) {
        launch();
    }
}