package com.example.urbanmobility.booking;
import com.example.urbanmobility.PaymentService;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.exception.PaymentDeclinedException;
import com.example.urbanmobility.transportation.Transport;
import com.example.urbanmobility.transportation.TransportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
class BookingServiceUnitTest {

    // Variable
    private Transport transport;
    private Account account;

    @Mock
    private PaymentService paymentService;

    @Mock
    private TransportService transportService;

    @InjectMocks
    private BookingService bookingService;


    @BeforeEach
    public void setup() {
        transport = Transport.builder()
                .id(1L)
                .departure("New York")
                .arrival("Los Angelse")
                .transportType("Flight")
                .estDeparture("2023-09-27")
                .estArrival("2023-09-28")
                .price(500.0)
                .supplier("Example Airlines")
                .build();

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

    /*
    Class: BookingService
    Method: makeBooking
    Type of test: Unit test

    Description: System should provide a way for the user to make a new booking
    */

    @Test
    public void ShouldThrowException_WhenPaymentFail(){
        // Arrange
        long transportId = transport.getId();
        long accountId = account.getId();
        given(paymentService.makePayment(1L)).willReturn(false);

        // Act
        assertThrows(PaymentDeclinedException.class,
                () -> bookingService.makeBooking(transportId, accountId));
    }

    @Test
    public void ShouldReturnMessage_IfPaymentWasSuccessful(){
        // Arrange
        long transportId = transport.getId();
        long accountId = account.getId();
        given(paymentService.makePayment(1L)).willReturn(true);
        given(transportService.updateRouteById(transportId, accountId)).willReturn(transport);

        // Act
       String message =  bookingService.makeBooking(transportId,accountId);

        // Assert
        Assertions.assertEquals("Booking was successful", message);
    }


    /*
    Class: BookingService
    Method: removeBooking
    Type of test: Unit test
    Description: System should provide a way for the user to remove their bookings
    */

    @Test
    public void ShouldReturnMessage_IfBookingWasDeleted(){
        // Arrange
        long transportId = transport.getId();

        // Act
        String message =  bookingService.removeBooking(transportId);

        // Assert
        Assertions.assertEquals("Booking was remove", message);
    }
}

