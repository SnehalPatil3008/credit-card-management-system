package com.ofss.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ofss.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}