package com.example.financeiro.controller;

import com.example.financeiro.model.Transaction;
import com.example.financeiro.service.TransactionService;
import com.example.financeiro.util.FXMLUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class TableController {

    @FXML private TableView<Transaction> table;
    @FXML private TableColumn<Transaction, String> colDesc;
    @FXML private TableColumn<Transaction, String> colType;
    @FXML private TableColumn<Transaction, String> colCategory;
    @FXML private TableColumn<Transaction, String> colDate;
    @FXML private TableColumn<Transaction, String> colAmount;

    private final TransactionService service = TransactionService.getInstance();
    private static Transaction selected;

    public static Transaction getSelected() {
        return selected;
    }

    public static void setSelected(Transaction t) {
        selected = t;
    }
    @FXML
    public void initialize() {
        colDesc.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDescription()));
        colType.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getType()));
        colCategory.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCategory()));
        colAmount.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.valueOf(c.getValue().getAmount())));
        colDate.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        ));

        table.getItems().addAll(service.all());
    }

    @FXML
    public void onEdit() {
        Transaction selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            alert("Selecione um item para editar.");
            return;
        }

        setSelected(selected);

        Stage stage = (Stage) table.getScene().getWindow();
        FXMLUtils.changeScene(stage, "/fxml/transaction_form.fxml");
    }

    @FXML
    public void onDelete() {
        Transaction t = table.getSelectionModel().getSelectedItem();

        if (t == null) {
            alert("Selecione um item para excluir.");
            return;
        }

        service.delete(t);
        table.getItems().remove(t);
    }

    @FXML
    public void onBack() {
        Stage stage = (Stage) table.getScene().getWindow();
        FXMLUtils.changeScene(stage, "/fxml/main.fxml");
        table.getItems().setAll(service.all());
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        a.showAndWait();
    }
}
