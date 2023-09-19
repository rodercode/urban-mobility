package com.example.urbanmobility.service;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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
        // Arrange - given that the driverRepo.save() method will return driverOne
        given(accountRepository.save(account)).willReturn(account);

        // test createDriver()
        Driver savedDriver = driverService.createDriver(driverOne);

        // Make sure it returns driver
        assertThat(savedDriver).isNotNull();
        verify(driverRepo, times(1)).save(driverOne);
        verifyNoMoreInteractions(driverRepo);
    }








}