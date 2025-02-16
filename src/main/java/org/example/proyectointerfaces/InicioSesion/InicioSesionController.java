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
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.proyectointerfaces.Hijos.HijosDAO;
import org.example.proyectointerfaces.Monitores.MonitoresDAO;
import org.example.proyectointerfaces.Registro.RegistroController;
import org.example.proyectointerfaces.Sincronizacion;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.Tutores_hijos.Tutores_hijosDAO;
import org.example.proyectointerfaces.VentanaTutoresLegales.VentanaTutoresController;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controlador para la ventana de inicio de sesión de la aplicación.
 * Maneja la autenticación de usuarios, la selección de idioma y la navegación entre ventanas.
 */
public class InicioSesionController {
    @FXML
    private TextField usuario;

    @FXML
    private TextFlow textFlow;
    @FXML
    private TextField password;

    @FXML
    private MenuButton idiomas;

    @FXML
    private Button botonAyuda;

    @FXML
    private Label errorGlobal, labelCrearCuenta, errorUsuario, errorPassword, labelInicioSesion, labelDNI, labelContrasena, labelCampoObligatorio;

    @FXML
    private Button botonRegistrarse;

    @FXML
    private Button botonIniciarSesion;

    TutoresLegalesDAO tutoresDAO = new TutoresLegalesDAO();
    MonitoresDAO monitoresDAO = new MonitoresDAO();
    HijosDAO hijosDAO = new HijosDAO();
    Tutores_hijosDAO tutoresHijosDAO = new Tutores_hijosDAO();
    Sincronizacion sincronizacion = new Sincronizacion(tutoresDAO, monitoresDAO, hijosDAO, tutoresHijosDAO);

    //Crea 3 items para el menuButton
    MenuItem español = new MenuItem("Español");
    MenuItem ingles = new MenuItem("Ingles");
    MenuItem frances = new MenuItem("Frances");

    //Crea el resource para establecer el idioma por defecto
    ResourceBundle bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));

    /**
     * Metodo de inicialización del controlador. Configura eventos y opciones de idioma.
     */
    @FXML
    public void initialize() {
        errorUsuario.setVisible(false);
        errorPassword.setVisible(false);
        errorGlobal.setVisible(false);

        // Añadir eventos de cambio de texto en los campos
        usuario.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(usuario, errorUsuario));
        password.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(password, errorPassword));

        // Añadir idiomas al menú
        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));
        ingles.setOnAction(e -> cambiarIdioma("Ingles"));
        frances.setOnAction(e -> cambiarIdioma("Frances"));
    }

    /**
     * Limpia el estilo de los campos de entrada cuando el usuario empieza a escribir.
     *
     * @param field      Campo de entrada.
     * @param errorLabel Etiqueta de error asociada al campo.
     */
    private void limpiarEstilo(TextField field, Label errorLabel) {
        field.setStyle("-fx-border-radius: 2px; -fx-border-color: transparent; -fx-border-width: 1px;");
        errorLabel.setVisible(false);
    }

    /**
     * Muestra un mensaje de error general que desaparece después de 5 segundos.
     */
    public void mostrarErrorGlobal() {
        errorGlobal.setVisible(true);
        PauseTransition pausa = new PauseTransition(Duration.seconds(5));
        pausa.setOnFinished(event -> errorGlobal.setVisible(false));
        pausa.play();
    }

    /**
     * Metodo para actualizar el idioma en los campos de la interfaz.
     */
    private void actualizarIdioma() {
        //Actualiza los idiomas según los campos establecidos en los Resources
        labelInicioSesion.setText(bundle.getString("inicioSesion.Titulo"));
        labelCrearCuenta.setText(bundle.getString("inicioSesion.CrearCuenta"));
        labelContrasena.setText(bundle.getString("inicioSesion.Contrasena"));
        labelDNI.setText(bundle.getString("inicioSesion.DNI"));
        botonRegistrarse.setText(bundle.getString("inicioSesion.BotonRegistrarse"));
        botonIniciarSesion.setText(bundle.getString("inicioSesion.Titulo"));
        labelCampoObligatorio.setText(bundle.getString("inicioSesion.LabelCampoObligatorio"));

        if (errorUsuario.isVisible()) {
            errorUsuario.setText(bundle.getString("inicioSesion.ErrorUsuario"));
        }
        if (errorPassword.isVisible()) {
            errorPassword.setText(bundle.getString("inicioSesion.ErrorPassword"));
        }
        if (errorGlobal.isVisible()) {
            errorGlobal.setText(bundle.getString("inicioSesion.ErrorGlobal"));
        }
    }

    /**
     * Cambia el idioma de la interfaz según la selección del usuario.
     *
     * @param idiomaSeleccionado Idioma elegido.
     */
    private void cambiarIdioma(String idiomaSeleccionado) {
        if ("Español".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("es", "ES"));
            idiomas.setText("Español");
            labelInicioSesion.setLayoutX(125);
            textFlow.setLayoutX(24);
        } else if ("Ingles".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("en", "EN"));
            idiomas.setText("Ingles");
            labelInicioSesion.setLayoutX(170);
            textFlow.setLayoutX(60);
            labelCampoObligatorio.setLayoutX(170);
        } else if ("Frances".equals(idiomaSeleccionado)) {
            bundle = ResourceBundle.getBundle("resourceIdiomas", new Locale("fr", "FR"));
            idiomas.setText("Frances");
            labelInicioSesion.setLayoutX(130);
            textFlow.setLayoutX(80);
        }
        actualizarIdioma();
    }

    /**
     * Abre la pantalla de carga antes de cambiar de ventana.
     *
     * @param onfinish Acción a realizar después de la pantalla de carga.
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
        cargaImagen.setStyle("-fx-background-color: white; -fx-alignment: center;");

        // Crear la animación de rebote en la imagen
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), cargaImagen);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
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
     * Valida las credenciales y redirige a la ventana correspondiente.
     */
    @FXML
    public void nuevaPagina() {
        if (usuario.getText().isEmpty() || password.getText().isEmpty()) {
            errorGlobal.setText(bundle.getString("inicioSesion.ErrorGlobal"));
            mostrarErrorGlobal();
            return;
        }
        errorGlobal.setVisible(false);

        if (sincronizacion.getTutores(usuario.getText())) {
            if (!sincronizacion.comprobarContrasenaTutores(usuario.getText(), password.getText())) {
                errorPassword.setText(bundle.getString("inicioSesion.ErrorPassword"));
                errorPassword.setVisible(true);
                password.setStyle("-fx-border-color: red;-fx-border-radius: 2px;-fx-border-width: 1px;");
                return;
            }
            abrirVentana("/org/example/proyectointerfaces/ventanaTutores.fxml");
        } else if (sincronizacion.getMonitores(usuario.getText())) {
            if (!sincronizacion.comprobarContrasenaMonitores(usuario.getText(), password.getText())) {
                errorPassword.setText(bundle.getString("inicioSesion.ErrorPassword"));
                errorPassword.setVisible(true);
                password.setStyle("-fx-border-color: red;-fx-border-radius: 2px;-fx-border-width: 1px;");
                return;
            }
            abrirVentana("/org/example/proyectointerfaces/menuInformes.fxml");
        } else {
            usuario.setStyle("-fx-border-color: red;-fx-border-radius: 2px;-fx-border-width: 1px;");
            errorUsuario.setText(bundle.getString("inicioSesion.ErrorUsuario"));
            errorUsuario.setVisible(true);
        }
    }

    /**
     * Abre una nueva ventana con la ruta especificada.
     *
     * @param fxmlPath Ruta del archivo FXML.
     */
    private void abrirVentana(String fxmlPath) {
        pantallaCarga(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();
                Stage escenarioSecundario = new Stage();
                escenarioSecundario.initModality(Modality.APPLICATION_MODAL);
                escenarioSecundario.setScene(new Scene(root));

                // Si la ventana es la de tutores, inicializa el controlador correctamente
                if (fxmlPath.equals("/org/example/proyectointerfaces/ventanaTutores.fxml")) {
                    VentanaTutoresController controllerTutores = loader.getController();
                    controllerTutores.cargarVentana(usuario.getText(), sincronizacion);
                }

                // Cerrar ventana actual y abrir nueva
                Stage ventanaActual = (Stage) usuario.getScene().getWindow();
                ventanaActual.close();
                escenarioSecundario.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Abre la ventana de registro.
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

    /**
     * Metodo para abrir la ventana de ayuda.
     */
    @FXML
    private void abrirAyuda() {
        Stage ayudaStage = new Stage();
        ayudaStage.setTitle("Ayuda");

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Cargar el HTML desde los recursos del proyecto
        String url = getClass().getResource("/org/example/proyectointerfaces/index.html").toExternalForm();
        webEngine.load(url);

        Scene scene = new Scene(webView, 600, 400);
        ayudaStage.setScene(scene);
        ayudaStage.initModality(Modality.APPLICATION_MODAL);
        ayudaStage.show();
    }
}
