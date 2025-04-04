package com.luis.picpaysimplicadochallenger.service;

import com.luis.picpaysimplicadochallenger.domain.Transaction;
import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.transaction.TransactionDto;
import com.luis.picpaysimplicadochallenger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
        User sender = this.userService.findUserById(transactionDto.senderId());
        User receiver = this.userService.findUserById(transactionDto.receiverId());

        this.userService.validateTransation(sender, transactionDto.value());
        boolean isAuthorized = this.authorizationService.authorizeTransaction();
        if(!isAuthorized){
            throw new Exception("Transação não autorizada.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.value());
        newTransaction.setReceiver(receiver);
        newTransaction.setSender(sender);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setWallet(sender.getWallet().subtract(transactionDto.value()));
        receiver.setWallet(receiver.getWallet().add(transactionDto.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso.");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso.");

        return newTransaction;
    }


}
