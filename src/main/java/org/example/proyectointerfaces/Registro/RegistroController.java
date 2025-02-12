package org.example.proyectointerfaces.Registro;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDAO;
import org.example.proyectointerfaces.TutoresLegales.TutoresLegalesDTO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Controlador para la pantalla de registro de un tutor legal.
 * Permite registrar un nuevo tutor, validar los campos y gestionar los mensajes de error.
 */
public class RegistroController {
    @FXML
    private TextField nombre, apellidos, dni, telefono, email, direccion, codigoPostal;

    @FXML
    private DatePicker fechaNacimiento;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private MenuButton idiomas;

    @FXML
    private Button createAccount;

    @FXML
    private Label errorGlobal, errorNombre, errorApellidos, errorDNI, errorFecha, errorTelefono, errorEmail, errorDireccion, errorCodigoPostal, errorPassword;

    MenuItem español = new MenuItem("Español");
    MenuItem ingles = new MenuItem("Ingles");
    MenuItem frances = new MenuItem("Frances");

    /**
     * Inicializa los elementos de la interfaz y configura los eventos de los menús.
     */
    @FXML
    public void initialize() {
        // Agregar los eventos de cambio de texto en los TextFields
        nombre.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(nombre, errorNombre));
        apellidos.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(apellidos, errorApellidos));
        dni.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(dni, errorDNI));
        telefono.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(telefono, errorTelefono));
        email.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(email, errorEmail));
        direccion.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(direccion, errorDireccion));
        codigoPostal.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(codigoPostal, errorCodigoPostal));
        password.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(password, errorPassword));
        password1.textProperty().addListener((observable, oldValue, newValue) -> limpiarEstilo(password1, errorPassword));

        idiomas.getItems().addAll(español, ingles, frances);
        español.setOnAction(e -> cambiarIdioma("Español"));
        ingles.setOnAction(e -> cambiarIdioma("Ingles"));
        frances.setOnAction(e -> cambiarIdioma("Frances"));
    }

    /**
     * Metodo para cambiar el idioma de la interfaz.
     *
     * @param idiomaSeleccionado El idioma que se selecciona.
     */
    private void cambiarIdioma(String idiomaSeleccionado) {


    }

    private void limpiarEstilo(TextField field, Label errorLabel) {
        field.setStyle("-fx-border-radius: 5px; -fx-border-color: transparent; -fx-border-width: 1px;");
        errorLabel.setVisible(false); // Oculta el mensaje de error asociado
    }

    private void limpiarEstilo(PasswordField field, Label errorLabel) {
        field.setStyle("-fx-border-radius: 5px; -fx-border-color: transparent; -fx-border-width: 1px;");
        errorLabel.setVisible(false); // Oculta el mensaje de error asociado
    }

    /**
     * Método que se ejecuta al registrar al tutor.
     * Realiza validaciones de los campos y registra al tutor si no hay errores.
     */
    public void registrarse() {
        limpiarErrores(); // Limpiar mensajes de error anteriores

        // Obtener valores de los campos
        String nombreTx = this.nombre.getText().trim();
        String apellidosTx = this.apellidos.getText().trim();
        String dniTx = this.dni.getText().trim();
        String telefonoTx = this.telefono.getText().trim();
        String emailTx = this.email.getText().trim();
        String direccionTx = this.direccion.getText().trim();
        String codigoPostalTx = this.codigoPostal.getText().trim();
        String passwordTx = this.password.getText().trim();
        LocalDate fechaNacimiento = this.fechaNacimiento.getValue();

        boolean hayErrores = false;
        boolean todosCamposVacios = nombreTx.isEmpty() && apellidosTx.isEmpty() && dniTx.isEmpty() && telefonoTx.isEmpty() && emailTx.isEmpty()
                && direccionTx.isEmpty() && codigoPostalTx.isEmpty() && passwordTx.isEmpty() && fechaNacimiento == null;

        // Si todos los campos están vacíos, mostrar el mensaje global
        if (todosCamposVacios) {
            mostrarErrorGlobal();
            return;
        } else {
            errorGlobal.setVisible(false);
        }

        // Validaciones individuales para cada campo
        if (nombreTx.isEmpty()) {
            errorNombre.setVisible(true);
            nombre.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (apellidosTx.isEmpty()) {
            errorApellidos.setVisible(true);
            apellidos.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (!dniTx.matches("\\d{8}[A-Za-z]")) {
            errorDNI.setVisible(true);
            dni.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (!telefonoTx.matches("\\d{9}")) {
            errorTelefono.setVisible(true);
            telefono.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (!emailTx.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errorEmail.setVisible(true);
            email.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (direccionTx.isEmpty()) {
            errorDireccion.setVisible(true);
            direccion.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (!codigoPostalTx.matches("\\d{5}")) {
            errorCodigoPostal.setVisible(true);
            codigoPostal.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (passwordTx.length() < 6) {
            errorPassword.setVisible(true);
            password.setStyle("-fx-border-radius: 5px; -fx-border-color: red; -fx-border-width: 1px;");
            hayErrores = true;
        }

        if (fechaNacimiento == null) {
            errorDNI.setText("Fecha de nacimiento es obligatoria.");
            errorDNI.setVisible(true);
            hayErrores = true;
        } else if (fechaNacimiento.isAfter(LocalDate.now())) {
            errorDNI.setText("Fecha de nacimiento no válida.");
            errorDNI.setVisible(true);
            hayErrores = true;
        }

        if (hayErrores) return; // Si hay errores, no continúa

        // Crear el objeto y guardar en la base de datos
        TutoresLegalesDTO tutorLegal = new TutoresLegalesDTO(nombreTx, apellidosTx, dniTx, fechaNacimiento, telefonoTx, emailTx, direccionTx, codigoPostalTx, passwordTx);
        TutoresLegalesDAO tutoresLegalesDAO = new TutoresLegalesDAO();
        tutoresLegalesDAO.insertTutorLegal(tutorLegal);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Credenciales");
        alert.setContentText("Su usuario es su DNI y la contraseña la que ha establecido");
        alert.show();

        // Cerrar la ventana de registro y abrir la de inicio de sesión
        try {
            Stage stage = (Stage) this.nombre.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/inicioSesion.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setTitle("Iniciar Sesión");
            loginStage.setScene(new Scene(root, 400, 600));
            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Limpia todos los mensajes de error antes de validar
     */
    private void limpiarErrores() {
        errorNombre.setVisible(false);
        errorApellidos.setVisible(false);
        errorDNI.setVisible(false);
        errorTelefono.setVisible(false);
        errorEmail.setVisible(false);
        errorDireccion.setVisible(false);
        errorCodigoPostal.setVisible(false);
        errorPassword.setVisible(false);
    }

    /**
     * Metodo para redirigir al usuario a la pantalla de inicio de sesión.
     */
    @FXML
    private void inicioSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectointerfaces/inicioSesion.fxml"));
            Parent root = loader.load();

            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.setTitle("Iniciar Sesión");
            nuevaVentana.show();

            // Cerrar la ventana actual
            Stage ventanaActual = (Stage) createAccount.getScene().getWindow();
            ventanaActual.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}