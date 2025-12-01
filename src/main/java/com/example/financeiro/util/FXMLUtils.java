package com.example.financeiro.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class FXMLUtils {

    public static void changeScene(Stage stage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(FXMLUtils.class.getResource("/css/style.css")).toExternalForm());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
