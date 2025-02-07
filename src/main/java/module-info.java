module org.example.proyectointerfaces {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.proyectointerfaces to javafx.fxml;
    exports org.example.proyectointerfaces;
}