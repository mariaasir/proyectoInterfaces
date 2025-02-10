package org.example.proyectointerfaces.SelectorInformes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectorInformesApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SelectorInformesApplication.class.getResource("/org/example/proyectointerfaces/menuInformes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 646);
        stage.setTitle("Informes Orión");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
