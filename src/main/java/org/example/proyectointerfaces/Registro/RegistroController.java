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

    @FXML
    private Label errorGlobal, errorNombre, errorApellidos, errorDNI, errorFecha, errorTelefono, errorEmail, errorDireccion, errorCodigoPostal, errorPassword;


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

    public void registrarse() {
        // Limpiar mensajes de error anteriores
        limpiarErrores();

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

        // Verificar si todos los campos están vacíos
        boolean todosCamposVacios = nombre.isEmpty() && apellidos.isEmpty() && dni.isEmpty() && telefono.isEmpty() && email.isEmpty()
                && direccion.isEmpty() && codigoPostal.isEmpty() && password.isEmpty() && fechaNacimiento == null;

        // Si todos los campos están vacíos, mostrar el mensaje global
        if (todosCamposVacios) {
            errorGlobal.setText("Todos los campos deben estar rellenos.");
            errorGlobal.setVisible(true);
            return; // Terminar el método para no continuar con la validación
        } else {
            // Si hay al menos un campo relleno, ocultamos el mensaje global
            errorGlobal.setVisible(false);
        }

        // Validaciones individuales para cada campo
        if (nombre.isEmpty()) {
            errorNombre.setText("El nombre es obligatorio.");
            errorNombre.setVisible(true);
            hayErrores = true;
        }

        if (apellidos.isEmpty()) {
            errorApellidos.setText("Los apellidos son obligatorios.");
            errorApellidos.setVisible(true);
            hayErrores = true;
        }

        if (!dni.matches("\\d{8}[A-Za-z]")) {
            errorDNI.setText("Formato incorrecto (Ej: 12345678A).");
            errorDNI.setVisible(true);
            hayErrores = true;
        }

        if (!telefono.matches("\\d{9}")) {
            errorTelefono.setText("Debe tener 9 dígitos.");
            errorTelefono.setVisible(true);
            hayErrores = true;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errorEmail.setText("Correo inválido.");
            errorEmail.setVisible(true);
            hayErrores = true;
        }

        if (direccion.isEmpty()) {
            errorDireccion.setText("La dirección es obligatoria.");
            errorDireccion.setVisible(true);
            hayErrores = true;
        }

        if (!codigoPostal.matches("\\d{5}")) {
            errorCodigoPostal.setText("Código postal inválido.");
            errorCodigoPostal.setVisible(true);
            hayErrores = true;
        }

        if (password.length() < 6) {
            errorPassword.setText("Mínimo 6 caracteres.");
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




}