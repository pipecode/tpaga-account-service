package co.pipecode.labs.tpaga.accountservice.rest;

import co.pipecode.labs.tpaga.accountservice.model.TPagaAccount;
import co.pipecode.labs.tpaga.accountservice.model.TPagaTransaction;
import co.pipecode.labs.tpaga.accountservice.rest.domain.TPagaTransactionRequest;
import co.pipecode.labs.tpaga.accountservice.rest.domain.TPagaTransactionResponse;
import co.pipecode.labs.tpaga.accountservice.service.ITPagaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/account")
public class AccountRestController {

    @Autowired
    private ITPagaAccountService accountService;

    @PostMapping
    public ResponseEntity<TPagaTransactionResponse> createTransaction(
            @Valid
            @RequestBody(required = true) TPagaTransactionRequest request) {
        TPagaTransactionResponse response = new TPagaTransactionResponse();
        try {
            TPagaTransaction transaction = accountService.deposit(request);
            response.setTransaction(transaction);
        } catch (Exception ex) {
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TPagaAccount> getAccount() {
        TPagaAccount account = new TPagaAccount();
        try {
            account = accountService.getAccount();
        } catch (Exception ex) {
            return new ResponseEntity<>(account, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> resetAccount() {
        try {
            accountService.reset();
        } catch (Exception ex) {
            return new ResponseEntity<>("Error: " + ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Account successfully reset", new HttpHeaders(), HttpStatus.OK);
    }

}
