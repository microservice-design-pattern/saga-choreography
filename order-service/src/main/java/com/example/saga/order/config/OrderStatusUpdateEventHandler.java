package com.example.saga.order.config;

import com.example.saga.order.entity.PurchaseOrder;
import com.example.saga.order.repository.OrderRepository;
import com.example.saga.order.service.OrderStatusPublisher;
import com.javatechie.saga.commons.dto.OrderRequestDto;
import com.javatechie.saga.commons.event.OrderStatus;
import com.javatechie.saga.commons.event.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateEventHandler {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public void updateOrder(final Integer id, Consumer<PurchaseOrder> consumer) {
        System.out.println("ORDER-SERVICE OrderStatusUpdateEventHandler updateOrder() method called...");
        //repository.findAll();//this is just a fake call otherwise spring data jpa findById(UUID) will not work .
        this.repository
                .findById(id)
                .ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        //check payment status
        boolean isComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        //based on payment status modify order status
        OrderStatus orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isComplete) {
            this.orderStatusPublisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
        }
    }

    private OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder) {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrderId(purchaseOrder.getId());
        orderRequestDto.setUserId(purchaseOrder.getUserId());
        orderRequestDto.setProductId(purchaseOrder.getProductId());
        orderRequestDto.setAmount(purchaseOrder.getPrice());
        return orderRequestDto;
    }



}
