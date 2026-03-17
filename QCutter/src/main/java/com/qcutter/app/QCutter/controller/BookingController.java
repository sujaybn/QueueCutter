package com.qcutter.app.QCutter.controller;

import com.qcutter.app.QCutter.dto.TicketBookingEvent;
import com.qcutter.app.QCutter.services.BookingProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingProducer producer;

    @PostMapping
    public ResponseEntity<String> bookTicket(@RequestBody TicketBookingEvent event) {
        producer.sendBookingEvent(event);
        return ResponseEntity.accepted().body("Booking request received!");
    }
}
