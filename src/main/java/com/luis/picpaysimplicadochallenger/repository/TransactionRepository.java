package com.luis.picpaysimplicadochallenger.repository;

import com.luis.picpaysimplicadochallenger.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
