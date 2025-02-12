package org.example.proyectointerfaces.InicioSesion;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Registro.RegistroController;
import org.example.proyectointerfaces.SelectorInformes.SelectorInformesController;
import org.example.proyectointerfaces.Sincronizacion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.VentanaTutoresLegales.VentanaTutoresController;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    //Pantalla de carga de Orion con su logotipo.
    private void pantallaCarga(Runnable onfinish) {
        Stage cargaStage = new Stage();
        VBox cargaVBox = new VBox();
        cargaVBox.setSpacing(20);
        cargaVBox.setStyle("-fx-background-color: white; -fx-alignment: center;");

        // Cargar la imagen
        Image cargaLogoOrion = new Image(getClass().getResourceAsStream("/org/example/proyectointerfaces/Imagenes/estrellas.jpg"));
        ImageView cargaImagen = new ImageView(cargaLogoOrion);
        cargaImagen.setFitWidth(150);
        cargaImagen.setFitHeight(150);

        // Crear la animación de rebote en la imagen
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), cargaImagen);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);
        scaleTransition.setFromX(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play(); // Iniciar la animación

        // Etiqueta de carga con efecto de desvanecimiento
        Label loadingLabel = new Label("Cargando, por favor espere...");
        loadingLabel.setStyle("-fx-text-fill: #131a8e; -fx-font-size: 18px; -fx-font-weight: bold;");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), loadingLabel);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1.0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play(); // Iniciar la animación

        // Añadir elementos al VBox
        cargaVBox.getChildren().addAll(cargaImagen, loadingLabel);

        // Crear la escena y configurarla
        Scene loadingScene = new Scene(cargaVBox, 400, 629);
        cargaStage.setScene(loadingScene);
        cargaStage.setTitle("Cargando...");
        cargaStage.initModality(Modality.APPLICATION_MODAL);
        cargaStage.show();

        // Usar un ExecutorService para manejar la espera en un hilo separado
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                Thread.sleep(5000); // Mantener la pantalla de carga por 5 segundos
                Platform.runLater(() -> {
                    cargaStage.close(); // Cerrar la pantalla de carga en el hilo de JavaFX
                    if (onfinish != null) {
                        onfinish.run();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        });
    }



    //Cargar una nueva página cuando haces click en el botón de INICIAR SESIÓN.
    //Si el DNI introducido coincide con un Tutor Legal ( que no tiene permisos para generar informes,
    // te llevará a una ventana que mostrará toda su información)
    //Si el DNI coincide con un Monitor ( que SI tiene permisos para generar informes, la página te llevará a la ventana de generar informes).
    @FXML
    public void nuevaPagina() {
        if (usuario.getText().isEmpty() || password.getText().isEmpty()) {
            errorGlobal.setText("Ningún campo puede estar vacío");
            errorGlobal.setVisible(true);
            return;
        }
        errorGlobal.setVisible(false);

        pantallaCarga(() -> {
            if (sincronizacion.getTutores(usuario.getText())) {
                if (!sincronizacion.comprobarContrasenaTutores(usuario.getText(), password.getText())) {
                    Platform.runLater(() -> {
                        errorPassword.setText("La contraseña es incorrecta.");
                        errorPassword.setVisible(true);
                        password.setStyle("-fx-border-color: red;");
                    });
                } else {
                    Platform.runLater(() -> abrirVentana("/org/example/proyectointerfaces/ventanaTutores.fxml"));
                }
            } else if (sincronizacion.getMonitores(usuario.getText())) {
                if (!sincronizacion.comprobarContrasenaMonitores(usuario.getText(), password.getText())) {
                    Platform.runLater(() -> {
                        errorPassword.setText("La contraseña es incorrecta.");
                        errorPassword.setVisible(true);
                        password.setStyle("-fx-border-color: red;");
                    });
                } else {
                    Platform.runLater(() -> abrirVentana("/org/example/proyectointerfaces/menuInformes.fxml"));
                }
            } else {
                Platform.runLater(() -> {
                    usuario.setStyle("-fx-border-color: red;");
                    errorUsuario.setText("El usuario es incorrecto.");
                    errorUsuario.setVisible(true);
                });
            }
        });
    }


    private void abrirVentana(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Obtener el controlador desde el FXMLLoader
            VentanaTutoresController controllerTutores = loader.getController();

            // Llamar a recibirUsuario con los datos correctos
            controllerTutores.cargarVentana(usuario.getText(), sincronizacion);

            Stage escenarioSecundario = new Stage();
            escenarioSecundario.initModality(Modality.APPLICATION_MODAL);
            escenarioSecundario.setScene(new Scene(root));
            Stage ventanaActual = (Stage) usuario.getScene().getWindow();
            ventanaActual.close();
            escenarioSecundario.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
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
