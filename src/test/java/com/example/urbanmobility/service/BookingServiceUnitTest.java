package com.example.urbanmobility.service;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingServiceUnitTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private BookingService bookingService;







}