package org.example.proyectointerfaces.Registro;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.time.LocalDate;

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
    private PasswordField password;

    @FXML
    private PasswordField password1;



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


    public void registrarse(){
        String nombre = this.nombre.getText();
        String apellidos = this.apellidos.getText();
        String dni = this.dni.getText();
        String telefono = this.telefono.getText();
        String email = this.email.getText();
        String direccion = this.direccion.getText();
        String codigoPostal = this.codigoPostal.getText();
        String password = this.password.getText();
        LocalDate fechaNacimiento = this.fechaNacimiento.getValue();

        TutoresLegalesDTO tutorLegal = new TutoresLegalesDTO(nombre,apellidos,dni,fechaNacimiento,telefono,email,direccion,codigoPostal, password);
        TutoresLegalesDAO tutoresLegalesDAO = new TutoresLegalesDAO();
        tutoresLegalesDAO.insertTutorLegal(tutorLegal);

    }

}