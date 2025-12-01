package com.example.financeiro.controller;

import com.example.financeiro.service.TransactionService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.example.financeiro.util.FXMLUtils;

public class MainController {

    @FXML private Label balanceLabel;
    @FXML private PieChart categoryChart;

    private final TransactionService service = TransactionService.getInstance();

    @FXML
    public void initialize() {
        refresh();
    }

    private void refresh() {
        balanceLabel.setText(String.format("Saldo: R$ %.2f", service.balance()));

        categoryChart.getData().clear();
        service.totalByCategory().forEach((cat, val) -> {
            double magnitude = Math.abs(val);
            PieChart.Data data = new PieChart.Data(cat + " (R$ " + String.format("%.2f", val) + ")", magnitude);
            categoryChart.getData().add(data);
        });
    }

    @FXML
    public void openForm() {
        Stage stage = (Stage) balanceLabel.getScene().getWindow();
        FXMLUtils.changeScene(stage, "/fxml/transaction_form.fxml");
    }
}
