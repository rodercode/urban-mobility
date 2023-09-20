package com.example.urbanmobility.service;

import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    // Variables
    private Account account;

    @DisplayName("Set up account data for each test")
    @BeforeEach
    public void setup(){
        account = Account.builder()
                .username("Roder")
                .role("User")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .paymentMethod("swish")
                .isPaymentSet(true)
                .build();
    }

    /*
    Class: AccountService
    Method: createAccount
    Type of test: Integration test

    Description: System should provide a way for a user to create a new account.

    requirements
    1. size should one when fetch all account after create a new account
    2. save method in account Repository should not return null
    */

    @Test
    public void ShouldHaveSizeOne_WhenAccountIsSavedInDB(){
        // Act
        Account savedAccount = accountService.createAccount(account);

        // Assert
        assertThat(savedAccount).isNotNull();
        assertThat(accountRepository.findAll().size()).isEqualTo(1);
    }

    /*
    Class: AccountService
    Method: deleteAccount
    Type of test: Integration test

    Description: System should provide a way for a user to delete their own account.

    requirements
    1. Database should be empty after delete a user by id
    */

    @Test
    public void ShouldBeEmpty_AfterDeleteAccountById(){
        // Arrange
        accountRepository.save(account);
        long accountId = account.getId();

        // Act
        accountService.deleteAccountById(accountId);

        // Assert
        assertThat(accountRepository.findAll().size()).isEqualTo(0);
    }
}
