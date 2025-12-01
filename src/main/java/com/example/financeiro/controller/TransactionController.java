package com.example.financeiro.controller;

import com.example.financeiro.model.Transaction;
import com.example.financeiro.service.TransactionService;
import com.example.financeiro.util.FXMLUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class TransactionController {

    @FXML private TextField descriptionField;
    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> typeBox;
    @FXML private TextField categoryField;
    @FXML private DatePicker datePicker;

    private final TransactionService service = TransactionService.getInstance();

    @FXML
    public void initialize() {
        typeBox.getItems().addAll("Ganhos", "Gastos");
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    public void onSave(javafx.event.ActionEvent e) {
        try {
            String desc = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String type = typeBox.getValue();
            String category = categoryField.getText();
            LocalDate date = datePicker.getValue();

            if (desc == null || desc.isBlank() || type == null || category == null || date == null) {
                showAlert("Preencha todos os campos.");
                return;
            }

            Transaction t = new Transaction(desc, amount, type, category, date);
            service.save(t);

            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            FXMLUtils.changeScene(stage, "/fxml/main.fxml");
        } catch (NumberFormatException ex) {
            showAlert("Valor inválido. Use apenas números (ex: 123.45).");
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Erro ao salvar a transação.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
