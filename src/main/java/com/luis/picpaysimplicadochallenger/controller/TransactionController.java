package com.luis.picpaysimplicadochallenger.controller;

import com.luis.picpaysimplicadochallenger.domain.Transaction;
import com.luis.picpaysimplicadochallenger.dto.transaction.TransactionDto;
import com.luis.picpaysimplicadochallenger.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto data) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(data);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
