package com.example.financeiro.controller;

import com.example.financeiro.model.Transaction;
import com.example.financeiro.service.TransactionService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.example.financeiro.util.FXMLUtils;

import java.time.LocalDate;
import java.util.List;

public class MainController {

    @FXML private Label balanceLabel;
    @FXML private PieChart categoryChart;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;


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
    @FXML
    private void updateBalance(List<Transaction> list) {
        double total = list.stream()
                .mapToDouble(t -> "GANHOS".equals(t.getType()) ? t.getAmount() : -t.getAmount())
                .sum();
        balanceLabel.setText(String.format("Saldo: R$ %.2f", total));
    }
    private void updateChart(List<Transaction> list) {
        categoryChart.getData().clear();

        var map = list.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        Transaction::getCategory,
                        java.util.stream.Collectors.summingDouble(t -> "GANHOS".equals(t.getType()) ? t.getAmount() : -t.getAmount())
                ));

        map.forEach((cat, val) -> {
            PieChart.Data data = new PieChart.Data(
                    cat + " (R$ " + String.format("%.2f", val) + ")",
                    Math.abs(val) // pizza precisa de valores positivos
            );
            categoryChart.getData().add(data);
        });
    }

    public void onFilter() {
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();

        if (start == null || end == null) return;

        var filtered = service.all().stream()
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(end))
                .toList();

        updateChart(filtered);
        updateBalance(filtered);
    }
    @FXML
    public void onClearFilter() {
        startDate.setValue(null);
        endDate.setValue(null);

        // volta a mostrar tudo
        refresh();
    }

    @FXML
    public void openTransactions() {
        Stage stage = (Stage) balanceLabel.getScene().getWindow();
        FXMLUtils.changeScene(stage, "/fxml/transactions_table.fxml");
    }

}
