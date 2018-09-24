package co.pipecode.labs.tpaga.accountservice.repository.impl;

import co.pipecode.labs.tpaga.accountservice.model.TPagaAccount;
import co.pipecode.labs.tpaga.accountservice.model.TPagaTransaction;
import co.pipecode.labs.tpaga.accountservice.repository.ITPagaAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TPagaAccountRepositoryImpl implements ITPagaAccountRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TPagaAccountRepositoryImpl.class);

    private TPagaAccount account;

    public TPagaAccountRepositoryImpl() {
        account = new TPagaAccount();
    }

    @Override
    public TPagaTransaction deposit(String description, double value) {
        TPagaTransaction transaction = new TPagaTransaction();
        try {
            double previousBalance = account.getBalance();
            double currentBalance = account.getBalance() + value;

            transaction.setDescription(description);
            transaction.setValue(value);
            transaction.setPreviousBalance(previousBalance);
            transaction.setCurrentBalance(currentBalance);

            List<TPagaTransaction> transactionList = new ArrayList<>();
            transactionList.addAll(account.getTransactions());
            transactionList.add(transaction);

            account.setBalance(currentBalance);
            account.setTransactions(transactionList);

            LOGGER.info("Depositing: {} Previous Balance: {} Current Balance: {}", value, previousBalance, currentBalance);
        } catch (Exception ex) {
            LOGGER.error("Error in Repository::deposit ", ex);
        }
        return transaction;
    }

    @Override
    public TPagaAccount getAccount() {
        TPagaAccount account = new TPagaAccount();
        try {
            account = this.account;
        } catch (Exception ex) {
            LOGGER.error("Error in Repository::getAccount ", ex);
        }
        return account;
    }

    @Override
    public void reset() {
        try {
            account.setBalance(0f);
            account.getTransactions().clear();
            LOGGER.info("Successful Reset");
        } catch (Exception ex) {
            LOGGER.error("Error in Repository::reset ", ex);
        }
    }

}
