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

    @KafkaListener(topics = "orders", groupId = "ticket-service-group")
    public void consume(String jsonMessage) throws Exception {
        // Note: We are now allowing the exception to propagate

        TicketBookingEvent event = objectMapper.readValue(jsonMessage, TicketBookingEvent.class);

        // Logic check: If user ID is "POISON_PILL", simulate a failure
        if ("POISON_PILL".equals(event.userId())) {
            System.err.println("POISON_PILL detected! Simulating processing failure for Ticket ID: " + event.ticketId());
            throw new RuntimeException("Simulated processing failure for DLT testing");
        }

        System.out.println("Processing Ticket ID: " + event.ticketId());
        simulateProcessing(event);
    }

    private void simulateProcessing(TicketBookingEvent event) {
        System.out.println("Success: Ticket " + event.ticketId() + " is now CONFIRMED");
    }
}