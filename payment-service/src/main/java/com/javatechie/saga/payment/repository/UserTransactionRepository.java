package com.javatechie.saga.payment.repository;

import com.javatechie.saga.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {
}
