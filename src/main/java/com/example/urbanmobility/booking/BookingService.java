package com.example.urbanmobility.booking;
import com.example.urbanmobility.PaymentService;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.exception.PaymentDeclinedException;
import com.example.urbanmobility.transportation.TransportService;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private TransportService transportService;
    private PaymentService paymentService;

    public BookingService(TransportService transportService, PaymentService paymentService) {
        this.transportService = transportService;
        this.paymentService = paymentService;
    }

    public String makeBooking(long transportId, long accountId) {
        if(!paymentService.makePayment(accountId)){
            throw new PaymentDeclinedException("Invalid payment");
        }
        transportService.updateRouteById(transportId, accountId);
        return "Booking was successful";
    }

    public String removeBooking(long transportId) {
        transportService.updateRouteById(transportId);
        return "Booking was remove";
    }
}
