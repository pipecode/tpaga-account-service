package co.pipecode.labs.tpaga.accountservice.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonPropertyOrder({"transactionDetail", "transactionValue"})
public class TPagaTransactionRequest implements Serializable {

    @NotNull
    @JsonProperty("transactionDetail")
    private String transactionDetail;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonProperty("transactionValue")
    private Double transactionValue;

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
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
