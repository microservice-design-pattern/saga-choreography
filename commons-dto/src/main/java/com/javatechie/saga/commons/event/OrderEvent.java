package com.javatechie.saga.commons.event;

import com.javatechie.saga.commons.dto.OrderRequestDto;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderEvent implements Event{


    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    public OrderEvent() {
    }

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
