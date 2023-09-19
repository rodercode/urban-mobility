package com.example.urbanmobility.service;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    public void setup(){
         account = Account.builder()
                .username("Roder")
                .role("User")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .creditDetail("1234-5678-9012-3456")
                .isPaymentSet(true)
                .build();
    }

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

        // Act
        assertThrows(IllegalArgumentException.class,
                () -> accountService.createAccount(account));
        verify(accountRepository, times(1)).findByUsername(account.getUsername());
    }

    @Test
    public void ShouldThrowException_IfEmailAlreadyExist(){
        // Arrange
        given(accountRepository.findByEmail(account.getEmail())).willReturn(account);

        // Act
        assertThrows(IllegalArgumentException.class,
                () -> accountService.createAccount(account));
        verify(accountRepository, times(1)).findByEmail(account.getEmail());
    }
}