module org.example.proyectointerfaces {
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires javafx.controls;
    requires javafx.web;
    requires java.desktop;


    opens org.example.proyectointerfaces to javafx.fxml;

    exports org.example.proyectointerfaces.InicioSesion;
    opens org.example.proyectointerfaces.InicioSesion to javafx.fxml;

    exports org.example.proyectointerfaces.VentanaTutoresLegales;
    opens org.example.proyectointerfaces.VentanaTutoresLegales;

    exports org.example.proyectointerfaces.Registro;
    opens org.example.proyectointerfaces.Registro to javafx.fxml;

    exports org.example.proyectointerfaces.SelectorInformes;
    opens org.example.proyectointerfaces.SelectorInformes to javafx.fxml;



    opens org.example.proyectointerfaces.Informes to javafx.fxml;
}