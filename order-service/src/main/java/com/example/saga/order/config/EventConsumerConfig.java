package com.example.saga.order.config;

import com.javatechie.saga.commons.event.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {


    @Autowired
    private OrderStatusUpdateEventHandler orderEventHandler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        System.out.println("ORDER-SERVICE EventConsumerConfig paymentEventConsumer() method called...");
        return (payment)-> orderEventHandler.updateOrder(payment.getPaymentRequestDto().getOrderId(), po->{
            po.setPaymentStatus(payment.getPaymentStatus());
        });
    }

}
