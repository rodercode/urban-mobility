package com.example.urbanmobility.booking;

import com.example.urbanmobility.transportation.Transport;
import com.example.urbanmobility.transportation.TransportService;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/booking/transport")
@RestController
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PutMapping("/{transportId}/account/{accountId}")
    public String makeBooking(
            @PathVariable("transportId") long transportId,
            @PathVariable("accountId") long accountId
    ) {
        bookingService.makeBooking(transportId, accountId);
        return "Booking has been successfully!";
    }

    @PutMapping("/{transportId}")
    public String removeBooking(@PathVariable("transportId") long transportId) {
        bookingService.removeBooking(transportId);
        return "Booking has been successfully removed!";
    }

}
