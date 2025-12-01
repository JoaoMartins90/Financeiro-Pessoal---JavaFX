package com.example.financeiro.controller;

import com.example.financeiro.util.FXMLUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    public void onLogin(ActionEvent e) {
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        FXMLUtils.changeScene(stage, "/fxml/main.fxml");
    }
}
