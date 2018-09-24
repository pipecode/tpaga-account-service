package co.pipecode.labs.tpaga.accountservice.rest;

import co.pipecode.labs.tpaga.accountservice.rest.domain.TPagaTransactionRequest;
import co.pipecode.labs.tpaga.accountservice.spring.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class AccountServiceRestTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createAccountTransactionTest() throws Exception {

        TPagaTransactionRequest request = new TPagaTransactionRequest();

        request.setTransactionDetail("Translado desde cuenta TPaga");
        request.setTransactionValue(50.00);

        mvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.transaction.currentBalance", is(50.00)));

    }

    @Test
    public void createAccountTransactionWithoutBodyTest() throws Exception {

        mvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getAccountInfoTest() throws Exception {

        mvc.perform(get("/api/account")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.balance", notNullValue()));

    }

    @Test
    public void resetAccountTest() throws Exception {

        mvc.perform(put("/api/account"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account successfully reset"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));

    }
}
