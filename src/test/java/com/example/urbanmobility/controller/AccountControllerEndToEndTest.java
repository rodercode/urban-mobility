package com.example.urbanmobility.controller;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerEndToEndTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;
    private String jsonAccount;

    @BeforeEach
    public void setup() throws JsonProcessingException {
        // Variable
        account = Account.builder()
                .username("Roder")
                .role("User")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .paymentMethod("swish")
                .isPaymentSet(true)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        jsonAccount = mapper.writeValueAsString(account);
    }

    @Test
    @DisplayName("check if post endpoint return status code 201 and account object")
    public void ShouldReturnCreateStatusCode_AndReturnAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAccount)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.username", Matchers.is("Roder")))
                .andExpect(jsonPath("$.email", Matchers.is("Roder@example.com")));

    }

    @Test
    @DisplayName("check if post endpoint return status code 409")
    public void ShouldReturnConflictStatusCode () throws Exception {
        // Arrange
        accountRepository.save(account);

        // Act
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAccount)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }





    @Test
    @DisplayName("Test Delete end point -> /api/delete/{accountId}")
    public void deleteAccountAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/account/{accountId}", 1L))
                        .andExpect(status().isOk())
                        .andExpect(content().string("Account was deleted successfully"));
    }

    @Test
    @DisplayName("Test Update end point -> /api/account/{accountId}")
    public void updateAccountApi() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/account/{accountId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAccount)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", Matchers.is(1)))
                        .andExpect(jsonPath("$.username", Matchers.is("Roder")))
                        .andExpect(jsonPath("$.email", Matchers.is("Roder@example.com")));
    }
}