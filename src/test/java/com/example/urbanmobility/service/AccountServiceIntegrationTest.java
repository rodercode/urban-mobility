package com.example.urbanmobility.service;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
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
                .id(1L)
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
