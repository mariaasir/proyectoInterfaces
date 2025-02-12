package org.example.proyectointerfaces.SelectorInformes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aplicación que muestra la ventana principal para seleccionar informes.
 */
public class SelectorInformesApplication extends Application {
    /**
     * Inicia la aplicación, carga el archivo FXML y muestra la ventana principal.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si no se puede cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SelectorInformesApplication.class.getResource("/org/example/proyectointerfaces/menuInformes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 646);
        stage.setTitle("Informes Orión");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo principal para lanzar la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
