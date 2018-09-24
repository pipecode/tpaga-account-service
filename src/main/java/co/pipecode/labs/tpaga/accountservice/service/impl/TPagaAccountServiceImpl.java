package co.pipecode.labs.tpaga.accountservice.service.impl;

import co.pipecode.labs.tpaga.accountservice.model.TPagaAccount;
import co.pipecode.labs.tpaga.accountservice.model.TPagaTransaction;
import co.pipecode.labs.tpaga.accountservice.repository.ITPagaAccountRepository;
import co.pipecode.labs.tpaga.accountservice.rest.domain.TPagaTransactionRequest;
import co.pipecode.labs.tpaga.accountservice.service.ITPagaAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TPagaAccountServiceImpl implements ITPagaAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TPagaAccountServiceImpl.class);

    private Lock balanceChangeLock;
    private Condition balanceChangedCondition;

    @Autowired
    private ITPagaAccountRepository accountRepository;

    public TPagaAccountServiceImpl() {
        balanceChangeLock = new ReentrantLock();
        balanceChangedCondition = balanceChangeLock.newCondition();
    }

    @Override
    public TPagaTransaction deposit(TPagaTransactionRequest transactionRequest) {
        TPagaTransaction transaction = new TPagaTransaction();
        balanceChangeLock.lock();
        try {
            transaction = accountRepository.deposit(transactionRequest.getTransactionDetail(), transactionRequest.getTransactionValue());
            balanceChangedCondition.signalAll();
        } catch (Exception ex) {
            LOGGER.error("Error in Service::deposit ", ex);
        } finally {
            balanceChangeLock.unlock();
        }
        return transaction;
    }

    @Override
    public TPagaAccount getAccount() {
        TPagaAccount account = new TPagaAccount();
        try {
            account = accountRepository.getAccount();
        } catch (Exception ex) {
            LOGGER.error("Error in Service::getAccount ", ex);
        }
        return account;
    }

    @Override
    public void reset() {
        balanceChangeLock.lock();
        try {
            accountRepository.reset();
            balanceChangedCondition.signalAll();
        } catch (Exception ex) {
            LOGGER.error("Error in Service::reset ", ex);
        } finally {
            balanceChangeLock.unlock();
        }
    }

}
