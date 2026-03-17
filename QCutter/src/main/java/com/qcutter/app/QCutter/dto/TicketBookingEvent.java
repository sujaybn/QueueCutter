package com.qcutter.app.QCutter.dto;

public record TicketBookingEvent(
        String ticketId,
        String userId,
        String eventName,
        Double price
) {}