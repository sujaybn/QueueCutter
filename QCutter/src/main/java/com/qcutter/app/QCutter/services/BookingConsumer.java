package com.qcutter.app.QCutter.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcutter.app.QCutter.dto.TicketBookingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingConsumer {

    private final ObjectMapper objectMapper;

    // This method triggers automatically whenever a new message hits "orders"
    @KafkaListener(topics = "orders", groupId = "ticket-service-group")
    public void consume(String jsonMessage) {
        try {
            // Convert the JSON string back into our Java Record
            TicketBookingEvent event = objectMapper.readValue(jsonMessage, TicketBookingEvent.class);

            System.out.println("Consumer Received: " + event.eventName());
            System.out.println("Processing Ticket ID: " + event.ticketId());

            // This is where you'd eventually call a DB or Payment Service
            simulateProcessing(event);

        } catch (Exception e) {
            System.err.println("Error parsing message: " + e.getMessage());
        }
    }

    private void simulateProcessing(TicketBookingEvent event) {
        System.out.println("Success: Ticket " + event.ticketId() + " is now CONFIRMED for " + event.userId());
    }
}