package com.example.financeiro.controller;

import com.example.financeiro.model.Transaction;
import com.example.financeiro.service.TransactionService;
import com.example.financeiro.util.FXMLUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

public class TableController {

    @FXML private TableView<Transaction> table;

    @FXML private TableColumn<Transaction, String> colDescription;
    @FXML private TableColumn<Transaction, String> colAmount;
    @FXML private TableColumn<Transaction, String> colType;
    @FXML private TableColumn<Transaction, String> colCategory;
    @FXML private TableColumn<Transaction, String> colDate;

    private final TransactionService service = TransactionService.getInstance();

    @FXML
    public void initialize() {

        colDescription.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        colAmount.setCellValueFactory(data -> new SimpleStringProperty(String.format("R$ %.2f", data.getValue().getAmount())));
        colType.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
        colCategory.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        colDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate().toString()));

        table.getItems().setAll(service.all());
    }

    @FXML
    public void onBack() {
        Stage stage = (Stage) table.getScene().getWindow();
        FXMLUtils.changeScene(stage, "/fxml/main.fxml");
    }
}
