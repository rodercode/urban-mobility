package com.example.urbanmobility.booking;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.transportation.TransportService;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private TransportService transportService;

    public BookingService(TransportService transportService) {
        this.transportService = transportService;
    }

    public void makeBooking(long transportId, long accountId) {
//        if(paymentService.checkPayment == "Invalid payment"){
//            throw new InvalidInputException("Payment was not successful");
//        }
        transportService.updateRouteById(transportId, accountId);
    }

    public void removeBooking(long transportId) {
        transportService.updateRouteById(transportId);
    }
}
