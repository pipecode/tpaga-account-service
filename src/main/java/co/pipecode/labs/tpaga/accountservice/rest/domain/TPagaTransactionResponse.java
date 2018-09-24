package co.pipecode.labs.tpaga.accountservice.rest.domain;

import co.pipecode.labs.tpaga.accountservice.model.TPagaTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonPropertyOrder({"transaction"})
public class TPagaTransactionResponse implements Serializable {

    @NotNull
    @JsonProperty("transaction")
    private TPagaTransaction transaction;

    public TPagaTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(TPagaTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        ObjectWriter ow = new ObjectMapper().writer();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

}
