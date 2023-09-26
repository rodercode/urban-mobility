package com.example.urbanmobility.account;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.exception.InvalidPermissionException;
import com.example.urbanmobility.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private Account inputAccount;

    @BeforeEach
    public void setup(){
         account = Account.builder()
                 .id(1L)
                .username("Roder")
                .role("User")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .paymentMethod("1234-5678-9012-3456")
                .isPaymentSet(true)
                .build();

        inputAccount = Account.builder()
                .id(1L)
                .username("Lisa")
                .role("User")
                .email("Lisa@example.com")
                .phone("0812345678")
                .paymentHistory(0)
                .paymentMethod("swish")
                .isPaymentSet(true)
                .build();
    }

    /*
    Class: AccountService
    Method: createAccount
    Type of test: Unit test

    Description: System should provide a way for a user to create a new account.

    requirements
    1. Should return Object Account
    2. Should throw exception if username and email already exist in DB
    3. Should not return null
    */

    @Test
    public void Should_ReturnAccount_When_CreateAccount(){
        // Arrange
        given(accountRepository.save(account)).willReturn(account);

        // Act
        Account savedAccount = accountService.createAccount(account);

        // Assert
        Assertions.assertThat(savedAccount).isNotNull();
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void ShouldThrowException_IfUsernameAlreadyExist(){
        // Arrange
        given(accountRepository.findByUsername(account.getUsername())).willReturn(account);

        // Act and Assert
        assertThrows(InvalidInputException.class,
                () -> accountService.createAccount(account));
        verify(accountRepository, times(1)).findByUsername(account.getUsername());
    }

    @Test
    public void ShouldThrowException_IfEmailAlreadyExist(){
        // Arrange
        given(accountRepository.findByEmail(account.getEmail())).willReturn(account);

        // Act and Assert
        assertThrows(InvalidInputException.class,
                () -> accountService.createAccount(account));
        verify(accountRepository, times(1)).findByEmail(account.getEmail());
    }

    @Test
    public void ShouldThrowException_WhenPhoneNumberNotContainsOnlyDigits(){
        // Arrange
        account.setPhone("gaglh45274214hg");

        // Act and Assert
        assertThrows(InvalidInputException.class,
                () -> accountService.createAccount(account));
    }



     /*
    Class: AccountService
    Method: deleteAccount
    Type of test: Unit test

    Description: System should provide a way for a user to delete their own account.

    requirements
    1. Should not return anything
    2. Should throw exception if user id does not exist in DB
    3. User should only be able to remove their own account
    */

    @Test
    public void ShouldDeleteAccount_WhenPassingValidId(){
        // Arrange
        long accountId = account.getId();
        given(accountRepository.existsById(accountId)).willReturn(true);
        willDoNothing().given(accountRepository).deleteById(accountId);

        // Act and Assert
        accountService.deleteAccountById(accountId);

        verify(accountRepository, times(1)).deleteById(accountId);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void ShouldThrowException_WhenPassingInvalidId(){
        // Arrange
        long accountId = 2L;
        given(accountRepository.existsById(accountId)).willReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> accountService.deleteAccountById(accountId));
        verify(accountRepository, never()).deleteById(accountId);
    }

     /*
    Class: AccountService
    Method: updateAccount
    Type of test: Unit test

    Description: System should provide a way for a user to update their details except for their role.

    requirements
    1. Should not return anything
    2. Should throw exception if user id does not exist in DB
    3. User should only be able to remove their own account
    */

    @Test
    public void ShouldThrowException_IsInvalidId(){
        // Arrange
        long accountId = account.getId();
        given(accountRepository.findById(accountId)).willReturn(Optional.empty());
        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> accountService.updateAccountById(accountId, account));
    }

    @Test
    public void ShouldNotReturnNull_WhenUpdatedWasSuccessful(){
        // Arrange
        long accountId = account.getId();
        given(accountRepository.findById(accountId)).willReturn(Optional.ofNullable(account));
        given(accountRepository.save(account)).willReturn(account);

        // Act
        Account updatedAccount = accountService.updateAccountById(accountId, account);

        // Assert
        assertThat(updatedAccount).isNotNull();
    }

    @Test
    public void ShouldReturnInputBackAfterUpdated(){
        // Arrange
        long accountId = account.getId();
        given(accountRepository.findById(accountId)).willReturn(Optional.ofNullable(account));
        given(accountRepository.save(inputAccount)).willReturn(inputAccount);

        // Act
        Account updatedAccount = accountService.updateAccountById(accountId, inputAccount);

        // Assert
        assertThat(updatedAccount.getUsername()).isEqualTo(inputAccount.getUsername());
        assertThat(updatedAccount.getId()).isEqualTo(inputAccount.getId());
        assertThat(updatedAccount.getEmail()).isEqualTo(inputAccount.getEmail());
    }

    @Test
    public void ShouldThrowException_WhenUserChangeTheirRole(){
        // Arrange
        long accountId = account.getId();
        given(accountRepository.findById(accountId)).willReturn(Optional.ofNullable(account));
        inputAccount.setRole("supplier");

        // Act and Assert
        assertThrows(InvalidPermissionException.class,
                () -> accountService.updateAccountById(accountId,inputAccount));

    }
}