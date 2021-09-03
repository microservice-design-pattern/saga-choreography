package com.example.saga.order.controller;

import com.example.saga.order.entity.PurchaseOrder;
import com.example.saga.order.service.OrderService;
import com.javatechie.saga.commons.dto.OrderRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderRequestDto.setOrderId(UUID.randomUUID());
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public List<PurchaseOrder> getOrders() {
        return orderService.getAll();
    }
}
