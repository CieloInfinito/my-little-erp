module com.example.mylittleerp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mylittleerp to javafx.fxml;
    exports com.example.mylittleerp;
}