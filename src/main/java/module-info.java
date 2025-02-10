module org.example.proyectointerfaces {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.proyectointerfaces to javafx.fxml;
    exports org.example.proyectointerfaces.InicioSesion;
    opens org.example.proyectointerfaces.InicioSesion to javafx.fxml;
    exports org.example.proyectointerfaces.Registro;
    opens org.example.proyectointerfaces.Registro to javafx.fxml;
}