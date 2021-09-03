package com.javatechie.saga.commons.event;

import com.javatechie.saga.commons.dto.PaymentRequestDto;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PaymentEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    public PaymentEvent() {
    }

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }
}
