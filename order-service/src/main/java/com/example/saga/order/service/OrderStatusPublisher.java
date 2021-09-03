package com.example.saga.order.service;

import com.javatechie.saga.commons.dto.OrderRequestDto;
import com.javatechie.saga.commons.event.OrderEvent;
import com.javatechie.saga.commons.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    //Sinks is a publisher , who will just help to emit the events
    private Sinks.Many<OrderEvent> orderSink;

    public void publishOrderEvent(OrderRequestDto dto, OrderStatus orderStatus){
        OrderEvent orderEvent=new OrderEvent(dto,orderStatus);
        orderSink.tryEmitNext(orderEvent);
    }
}

