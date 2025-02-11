package org.example.proyectointerfaces.InicioSesion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Registro.RegistroController;
import org.example.proyectointerfaces.SelectorInformes.SelectorInformesController;
import org.example.proyectointerfaces.Sincronizacion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.VentanaTutoresLegales.VentanaTutoresController;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class InicioSesionController {
    @FXML
    private TextField usuario;

    @FXML
    private TextField password;

    @FXML
    private MenuButton idiomas;

    @FXML
    private Label errorGlobal, labelCrearCuenta, errorUsuario, errorPassword;
    @FXML
    private Button botonRegistrarse;


    @FXML
    private Button botonIniciarSesion;

    TutoresLegalesDAO tutoresDAO = new TutoresLegalesDAO();
    MonitoresDAO monitoresDAO = new MonitoresDAO();
    Sincronizacion sincronizacion = new Sincronizacion(tutoresDAO, monitoresDAO);

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



    //Cargar una nueva página cuando haces click en el botón de INICIAR SESIÓN.
    //Si el DNI introducido coincide con un Tutor Legal ( que no tiene permisos para generar informes,
    // te llevará a una ventana que mostrará toda su información)
    //Si el DNI coincide con un Monitor ( que SI tiene permisos para generar informes, la página te llevará a la ventana de generar informes).
    @FXML
    public void nuevaPagina() {

        if (usuario.getText().isEmpty() || password.getText().isEmpty()) {
            errorGlobal.setText("Ningún campo puede estar vacio");
            errorGlobal.setVisible(true);
            return;
        } else {
            errorGlobal.setVisible(false);
        }




        if (sincronizacion.getTutores(usuario.getText())) {   //Si el DNI coincide con un tutor legal

            //Comprueba que la contraseña introducida es la correcta para ese usuario
            if (!sincronizacion.comprobarContrasenaTutores(usuario.getText(), password.getText())) {
                errorPassword.setText("La contraseña es incorrecta.");
                errorPassword.setVisible(true);
                password.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
            } else {

                FXMLLoader cargaLI = new FXMLLoader(getClass().
                        getResource("/org/example/proyectointerfaces/ventanaTutores.fxml"));
                Parent root = null;
                try {
                    root = cargaLI.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                VentanaTutoresController controladorLI = cargaLI.getController();
                Stage escenarioSecundario = new Stage();
                escenarioSecundario.initModality(Modality.APPLICATION_MODAL);
                escenarioSecundario.setScene(new Scene(root));
                Stage ventanaActual = (Stage) usuario.getScene().getWindow();
                ventanaActual.close();
                escenarioSecundario.showAndWait();
            }
        }



        if (sincronizacion.getMonitores(usuario.getText())) { //Si el DNI coincide con un Monitor

            //Comprueba que la contraseña introducida es la correcta para ese usuario
            if (!sincronizacion.comprobarContrasenaMonitores(usuario.getText(), password.getText())) {
                errorPassword.setText("La contraseña es incorrecta.");
                errorPassword.setVisible(true);
                password.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
            } else {


                FXMLLoader cargaLI = new FXMLLoader(getClass().
                        getResource("/org/example/proyectointerfaces/menuInformes.fxml"));
                Parent root = null;
                try {
                    root = cargaLI.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //Inicia el controlador
                SelectorInformesController controladorLI = cargaLI.getController();
                //Crea un nuevo escenario
                Stage escenarioSecundario = new Stage();
                escenarioSecundario.initModality(Modality.APPLICATION_MODAL);
                escenarioSecundario.setScene(new Scene(root));
                Stage ventanaActual = (Stage) usuario.getScene().getWindow();
                ventanaActual.close();      //Cierra la ventana actual
                escenarioSecundario.showAndWait();      //Muestra el nuevo escenario
            }
        }


        if (!sincronizacion.getMonitores(usuario.getText()) && !sincronizacion.getTutores(usuario.getText()) ){
            usuario.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
            errorUsuario.setText("El usuario es incorrecto.");
            errorUsuario.setVisible(true);
        }

    }





    //Metodo para que al hacer click en el botón de Registrase si aún no tienes cuenta, este botón te lleve a la página de registro.
    public void registrarse(){
        FXMLLoader cargaLI = new FXMLLoader(getClass().
                getResource("/org/example/proyectointerfaces/registro.fxml"));
        Parent root = null;
        try {
            root = cargaLI.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RegistroController controladorLI = cargaLI.getController();
        Stage escenarioSecundario = new Stage();
        escenarioSecundario.initModality(Modality.APPLICATION_MODAL);
        escenarioSecundario.setScene(new Scene(root));
        Stage ventanaActual = (Stage) usuario.getScene().getWindow();
        ventanaActual.close();
        escenarioSecundario.showAndWait();
    }


}