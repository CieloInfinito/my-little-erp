package com.example.mylittleerp;

import com.example.mylittleerp.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ERPApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ERPApplication.class.getResource("mainTabbedView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Little ERP");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}