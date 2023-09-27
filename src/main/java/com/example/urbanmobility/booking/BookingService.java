package com.example.urbanmobility.booking;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.transportation.TransportService;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private TransportService transportService;
    private AccountService accountService;

    public BookingService(TransportService transportService, AccountService accountService) {
        this.transportService = transportService;
        this.accountService = accountService;
    }

    public void makeBooking(long transportId, long accountId) {
        Account account = accountService.getAccountById(accountId).get();
        transportService.updateRouteById(transportId, accountId);
    }

    public void removeBooking(long transportId) {
        transportService.updateRouteById(transportId);
    }
}
