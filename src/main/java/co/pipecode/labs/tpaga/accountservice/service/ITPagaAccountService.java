package co.pipecode.labs.tpaga.accountservice.service;

import co.pipecode.labs.tpaga.accountservice.model.TPagaAccount;
import co.pipecode.labs.tpaga.accountservice.model.TPagaTransaction;
import co.pipecode.labs.tpaga.accountservice.rest.domain.TPagaTransactionRequest;

public interface ITPagaAccountService {

    TPagaTransaction deposit(TPagaTransactionRequest tPagaTransactionRequest);

    TPagaAccount getAccount();

    void reset();

}
