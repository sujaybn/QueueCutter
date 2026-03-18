package com.qcutter.app.QCutter.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcutter.app.QCutter.dto.TicketBookingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingProducer {

    private final KafkaTemplate<String, String> kafkaTemplate; // Note: Value is now String
    private final ObjectMapper objectMapper;

    public void sendBookingEvent(TicketBookingEvent event) {
        try {
            // Manually convert the record to a JSON string
            String jsonEvent = objectMapper.writeValueAsString(event);

            kafkaTemplate.send("orders", event.ticketId(), jsonEvent);
            System.out.println("Sent JSON to Kafka: " + jsonEvent);
        } catch (Exception e) {
            System.err.println("Error serializing event: " + e.getMessage());
        }
    }
}