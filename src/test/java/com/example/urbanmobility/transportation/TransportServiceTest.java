package com.example.urbanmobility.transportation;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.auth.AuthService;
import com.example.urbanmobility.exception.InvalidPermissionException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransportServiceTest {

    @Mock
    private TransportRepository transportRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private TransportService transportService;

    // Variable
    private Transport transport;
    private Account account;

    @BeforeEach
    public void setup(){
        transport = Transport.builder()
                .id(1L)
                .departure("City A")
                .arrival("City B")
                .supplier("Train Company")
                .transportType("Train")
                .estDeparture("2023-09-25T10:00:00")
                .estArrival("2023-09-25T12:00:00")
                .price(500)
                .build();

        account = Account.builder()
                .id(1L)
                .username("Roder")
                .role("Supplier")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .paymentMethod("1234-5678-9012-3456")
                .isPaymentSet(true)
                .build();


    }

    @Test
    public void ShouldNotReturnNull_WhenCreateTransport () {
        // Arrange
        long accountId = account.getId();

        given(transportRepository.save(transport)).willReturn(transport);

        // Act
        Transport createdTransport = transportService.createTransport(transport,accountId);

        // Assert
        Assertions.assertThat(createdTransport).isNotNull();
        verify(transportRepository, times(1)).save(transport);
    }
}