package com.example.urbanmobility.booking;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.transportation.TransportService;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private TransportService transportService;
    private AccountService accountService;

    public BookingService(TransportService transportService) {
        this.transportService = transportService;
    }

    public void makeBooking(long transportId, long accountId) {
        Account account = accountService.getAccountById(accountId).get();
//        if(!account.getPaymentSet().equals("true")){
//            throw new IllegalArgumentException("You have to set your payment method");
//        }
        transportService.updateRouteById(transportId, accountId);
    }

    public void removeBooking(long transportId) {
        transportService.updateRouteById(transportId);
    }
}
