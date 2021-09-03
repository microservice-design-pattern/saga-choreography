package com.example.saga.order.repository;

import com.example.saga.order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, UUID> {
}
