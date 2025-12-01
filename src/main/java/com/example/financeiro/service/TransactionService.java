package com.example.financeiro.service;

import com.example.financeiro.dao.TransactionDAO;
import com.example.financeiro.model.Transaction;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionService {

    private static final TransactionService instance = new TransactionService();

    public static TransactionService getInstance() {
        return instance;
    }

    private TransactionService() {}

    private final TransactionDAO dao = TransactionDAO.getInstance();

    public void save(Transaction t) {
        dao.save(t);
    }

    public void delete(Transaction t) {
        dao.delete(t);
    }

    public void update(Transaction original, Transaction edited) {
        dao.update(original, edited);
    }


    public List<Transaction> all() {
        return dao.findAll();
    }

    public double balance() {
        return all().stream()
                .mapToDouble(t -> "GANHOS".equals(t.getType()) ? t.getAmount() : -t.getAmount())
                .sum();
    }


    public Map<String, Double> totalByCategory() {
        return all().stream()
                .collect(Collectors.groupingBy(Transaction::getCategory,
                        Collectors.summingDouble(t -> "GANHOS".equals(t.getType()) ? t.getAmount() : -t.getAmount())));
    }
}
