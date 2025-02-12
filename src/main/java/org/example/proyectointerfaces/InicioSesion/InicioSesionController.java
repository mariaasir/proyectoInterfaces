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
import org.example.proyectointerfaces.Sincronizacion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controlador de la pantalla de inicio de sesión.
 * Gestiona la lógica de inicio de sesión, carga de pantalla,
 * cambio de idioma, y redirección a las ventanas correspondientes según el rol del usuario.
 */
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

    MenuItem español = new MenuItem("Español");
    MenuItem ingles = new MenuItem("Inglés");
    MenuItem frances = new MenuItem("Francés");

    /**
     * Inicializa los elementos de la interfaz, como el menu de idiomas.
     * Asocia las acciones de los items de menú con el método cambiarIdioma.
     */
    @FXML
    public void initialize() {
        //Añade los menuItems al MenuButton
        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));
        ingles.setOnAction(e -> cambiarIdioma("Ingles"));
        frances.setOnAction(e -> cambiarIdioma("Frances"));
    }

    /**
     * Cambia el idioma de la interfaz según la opción seleccionada.
     *
     * @param idiomaSeleccionado El idioma que el usuario elige.
     */
    private void cambiarIdioma(String idiomaSeleccionado) {


    }

    /**
     * Muestra una pantalla de carga con animaciones mientras se realiza una acción en segundo plano.
     *
     * @param onfinish Acción a ejecutar una vez que la pantalla de carga haya finalizado.
     */
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


    /**
     * Controla la lógica de inicio de sesión. Valida los datos ingresados por el usuario
     * y redirige a la ventana correspondiente dependiendo del tipo de usuario.
     */
    @FXML
    public void nuevaPagina() {
        if (usuario.getText().isEmpty() || password.getText().isEmpty()) {
            mostrarErrorGlobal();
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

    /**
     * Metodo para mostrar el error y hacer que a los 5 segundos desaparezca
     */
    public void mostrarErrorGlobal() {
        errorGlobal.setVisible(true);
        PauseTransition pausa = new PauseTransition(Duration.seconds(5));
        pausa.setOnFinished(event -> errorGlobal.setVisible(false));
        pausa.play();
    }

    /**
     * Abre una nueva ventana en la aplicación.
     *
     * @param fxmlPath La ruta del archivo FXML que define la interfaz de la nueva ventana.
     */
    private void abrirVentana(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
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

    /**
     * Abre la ventana de registro cuando el usuario hace clic en el botón de registrarse.
     */
    public void registrarse() {
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
