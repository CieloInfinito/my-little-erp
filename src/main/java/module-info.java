module com.example.mylittleerp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.mylittleerp to javafx.fxml;
    opens com.example.mylittleerp.controller to javafx.fxml;

    exports com.example.mylittleerp;
}