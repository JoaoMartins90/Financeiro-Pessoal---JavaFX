package com.example.financeiro.dao;

import com.example.financeiro.model.Transaction;
import com.example.financeiro.util.JsonStore;

import java.util.List;

public class TransactionDAO {

    private static final TransactionDAO instance = new TransactionDAO();
    public static TransactionDAO getInstance() { return instance; }

    private List<Transaction> list = JsonStore.loadList(Transaction.class);

    public void save(Transaction t) {
        list.add(t);
        JsonStore.saveList(list);
    }

    public void delete(Transaction t) {
        list.remove(t);
        JsonStore.saveList(list);
    }

    public List<Transaction> findAll() {
        return list;
    }

    public void update(Transaction original, Transaction edited) {
        int index = list.indexOf(original);
        if (index >= 0) {
            list.set(index, edited);
            JsonStore.saveList(list);
        }
    }
}

