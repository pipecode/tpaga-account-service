package co.pipecode.labs.tpaga.accountservice.model;

import java.util.ArrayList;
import java.util.List;

public class TPagaAccount {

    private double balance;

    private List<TPagaTransaction> transactions;

    public TPagaAccount() {
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TPagaTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TPagaTransaction> transactions) {
        this.transactions = transactions;
    }

}
