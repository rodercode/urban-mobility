package com.example.urbanmobility.auth;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountRepository;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.exception.InvalidPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceUnitTest {

    @Mock
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AuthService authService;

    // Variable
    private Account account;

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
    }


    @Test
    public void ShouldThrowException_IfAccountIsNotASupplier(){
        // Arrange
        given(accountService.getAccountById(account.getId())).willReturn(Optional.ofNullable(account));

        // Act
        assertThrows(InvalidPermissionException.class,
                () -> authService.validSupplier(account.getId()));
    }


}