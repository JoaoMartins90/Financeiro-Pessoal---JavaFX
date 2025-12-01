package com.example.financeiro.dao;

import com.example.financeiro.model.Transaction;
import com.example.financeiro.util.JsonStore;

import java.util.List;

public class TransactionDAO {

    private static final TransactionDAO instance = new TransactionDAO();
    public static TransactionDAO getInstance() { return instance; }

    private TransactionDAO() {}

    public List<Transaction> findAll() {
        return JsonStore.loadList(Transaction.class);
    }

    public void save(Transaction t) {
        List<Transaction> list = findAll();
        list.add(t);
        JsonStore.saveList(list);
    }
}

