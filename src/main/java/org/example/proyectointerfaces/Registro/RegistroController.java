package org.example.proyectointerfaces.Registro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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

    /**
     * Método que se ejecuta al registrar al tutor.
     * Realiza validaciones de los campos y registra al tutor si no hay errores.
     */
    public void registrarse() {
        limpiarErrores(); // Limpiar mensajes de error anteriores

        // Obtener valores de los campos
        String nombre = this.nombre.getText().trim();
        String apellidos = this.apellidos.getText().trim();
        String dni = this.dni.getText().trim();
        String telefono = this.telefono.getText().trim();
        String email = this.email.getText().trim();
        String direccion = this.direccion.getText().trim();
        String codigoPostal = this.codigoPostal.getText().trim();
        String password = this.password.getText().trim();
        LocalDate fechaNacimiento = this.fechaNacimiento.getValue();

        boolean hayErrores = false;
        boolean todosCamposVacios = nombre.isEmpty() && apellidos.isEmpty() && dni.isEmpty() && telefono.isEmpty() && email.isEmpty()
                && direccion.isEmpty() && codigoPostal.isEmpty() && password.isEmpty() && fechaNacimiento == null;

        // Si todos los campos están vacíos, mostrar el mensaje global
        if (todosCamposVacios) {
            errorGlobal.setVisible(true);
            return;
        } else {
            errorGlobal.setVisible(false);
        }

        // Validaciones individuales para cada campo
        if (nombre.isEmpty()) {
            errorNombre.setVisible(true);
            hayErrores = true;
        }

        if (apellidos.isEmpty()) {
            errorApellidos.setVisible(true);
            hayErrores = true;
        }

        if (!dni.matches("\\d{8}[A-Za-z]")) {
            errorDNI.setVisible(true);
            hayErrores = true;
        }

        if (!telefono.matches("\\d{9}")) {
            errorTelefono.setVisible(true);
            hayErrores = true;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errorEmail.setVisible(true);
            hayErrores = true;
        }

        if (direccion.isEmpty()) {
            errorDireccion.setVisible(true);
            hayErrores = true;
        }

        if (!codigoPostal.matches("\\d{5}")) {
            errorCodigoPostal.setVisible(true);
            hayErrores = true;
        }

        if (password.length() < 6) {
            errorPassword.setVisible(true);
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

        //Crear el objeto y guardar en la base de datos
        TutoresLegalesDTO tutorLegal = new TutoresLegalesDTO(nombre, apellidos, dni, fechaNacimiento, telefono, email, direccion, codigoPostal, password);
        TutoresLegalesDAO tutoresLegalesDAO = new TutoresLegalesDAO();
        tutoresLegalesDAO.insertTutorLegal(tutorLegal);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Credenciales");
        alert.setContentText("Su usuario es su DNI y la contraseña la que ha establecido");
        alert.show();

        // Cerrar la ventana de registro y abrir la de inicio de sesión
        try {
            // Obtener la ventana actual y cerrarla
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