package com.example.urbanmobility.account;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountRepository;
import com.example.urbanmobility.account.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountServiceIntegrationTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    // Variables
    private Account account;
    private Account inputAccount;
    private final long accountId = 1L;

    @BeforeEach
    public void setup(){
        // Previous object
        account = Account.builder()
                .id(accountId)
                .username("Roder")
                .role("User")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .paymentMethod("swish")
                .isPaymentSet(true)
                .build();

        // New Object
        inputAccount = Account.builder()
                .id(accountId)
                .username("Lisa")
                .role("User")
                .email("Lisa@example.com")
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

        // Act
        accountService.deleteAccountById(1L);

        // Assert
        assertThat(accountRepository.findAll().size()).isEqualTo(0);
    }

    /*
    Class: AccountService
    Method: updateAccount
    Type of test: Integration test

    Description: System should provide a way for a user to update their own account.

    requirements
    1. Account in database should be updated after update account by id
    */

    @Test
    public void ShouldChangeFetchAccount_WhenUpdated(){
        // Arrange
        long accountId = account.getId();
        accountRepository.save(account);
        Account fetchAccount = accountRepository.findById(account.getId()).get();

        // Act
        accountService.updateAccountById(accountId, inputAccount);
        Account fetchUpdated = accountRepository.findById(accountId).get();

        // Assert
        assertThat(fetchUpdated.getId()).isEqualTo(fetchAccount.getId());
        assertThat(fetchUpdated.getUsername()).isNotEqualTo(fetchAccount.getUsername());
        assertThat(fetchUpdated.getEmail()).isNotEqualTo(fetchAccount.getEmail());
    }
}
