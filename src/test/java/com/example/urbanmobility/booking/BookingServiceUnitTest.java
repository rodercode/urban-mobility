//package com.example.urbanmobility.booking;
//import com.example.urbanmobility.PaymentService;
//import com.example.urbanmobility.account.Account;
//import com.example.urbanmobility.transportation.Transport;
//import com.example.urbanmobility.transportation.TransportService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//class BookingServiceUnitTest {
//
//    @SpringBootTest
//    public class AccountServiceIntegrationTest {
//
//        // Variable
//        private Transport transport;
//
//
//        @Mock
//        private TransportService transportService;
//
//        @Mock
//        private PaymentService paymentService;
//
//        @InjectMocks
//        private BookingService bookingService;
//
//
//        @BeforeEach
//        public void setup() {
//            Transport transport = Transport.builder()
//                    .id(1L)
//                    .departure("New York")
//                    .arrival("Los Angelse")
//                    .transportType("Flight")
//                    .estDeparture("2023-09-27")
//                    .estArrival("2023-09-28")
//                    .price(500.0)
//                    .supplier("Example Airlines")
//                    .build();
//        }
//
//        @Test
//        public void test(){
//
//        }
//
//
//        @Test
//        public void Should_ReturnAccount_When_CreateAccount() {
//            // Arrange
//            given(accountRepository.save(account)).willReturn(account);
//
//            // Act
//            Account savedAccount = accountService.createAccount(account);
//
//            // Assert
//            Assertions.assertThat(savedAccount).isNotNull();
//            verify(accountRepository, times(1)).save(account);
//        }
//
//    }
//
//
//}