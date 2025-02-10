package org.example.proyectointerfaces.Registro;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class RegistroController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField dni;

    @FXML
    private TextField telefono;

    @FXML
    private TextField email;
    @FXML
    private TextField direccion;

    @FXML
    private TextField codigoPostal;

    @FXML
    private DatePicker fechaNacimiento;


    @FXML
    private MenuButton idiomas;

    //Crea 3 items para el menuButton
    MenuItem español = new MenuItem("Español");
    MenuItem ingles = new MenuItem("Ingles");
    MenuItem frances = new MenuItem("Frances");

    //Crea el resource para establecer el idioma por defecto
   // ResourceBundle bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));


    //Metodo para iniciar las variables
    @FXML
    public void initialize() {
        //Añade los menuItems al MenuButton
        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));       //Si esta seleccionado el menuItem español, ejecutará el código cambiarIdioma con el String español

        ingles.setOnAction(e -> cambiarIdioma("Ingles"));        //Si esta seleccionado el menuItem ingles, ejecutará el código cambiarIdioma con el String ingles

        frances.setOnAction(e -> cambiarIdioma("Frances"));      //Si esta seleccionado el menuItem frances, ejecutará el código cambiarIdioma con el String frances


    }


    private void cambiarIdioma(String idiomaSeleccionado) {


    }

}