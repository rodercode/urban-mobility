package com.example.urbanmobility.service;

import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountServiceIntegrationTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void ShouldHaveSizeOne_WhenAccountIsSavedInDB(){
        // Arrange
        Account account = Account.builder()
                .username("Roder")
                .role("User")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .creditDetail("1234-5678-9012-3456")
                .isPaymentSet(true)
                .build();

        Account savedAccount = accountService.createAccount(account);
        assertThat(savedAccount).isNotNull();
        assertThat(accountRepository.findAll().size()).isEqualTo(1);
    }
}
