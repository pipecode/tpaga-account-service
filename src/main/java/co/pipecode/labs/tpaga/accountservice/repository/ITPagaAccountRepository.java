package co.pipecode.labs.tpaga.accountservice.repository;

import co.pipecode.labs.tpaga.accountservice.model.TPagaAccount;
import co.pipecode.labs.tpaga.accountservice.model.TPagaTransaction;

public interface ITPagaAccountRepository {

    TPagaTransaction deposit(String description, double value);

    TPagaAccount getAccount();

    void reset();

}
