package com.luis.picpaysimplicadochallenger.service;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.transaction.TransactionDto;
import com.luis.picpaysimplicadochallenger.repository.TransactionRepository;
import com.luis.picpaysimplicadochallenger.ultis.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserService userService;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is OK.")
    void createTransactionCase1() throws Exception {
        Long id1 = 1L;
        Long id2 = 2L;
        User sender = new User(id1, "Maria", "Sousa", "999999999901", "maria@gmail.com", "12345", UserType.CLIENTE, new BigDecimal(10));
        User receiver = new User(id2, "Eduarda", "Martins", "999999999902", "eduarda@gmail.com", "12345", UserType.CLIENTE, new BigDecimal(10));

        when(userService.findUserById(id1)).thenReturn(sender);
        when(userService.findUserById(id2)).thenReturn(receiver);

        when(authorizationService.authorizeTransaction()).thenReturn(true);

        TransactionDto request = new TransactionDto(new BigDecimal(10), id1, id2);
        transactionService.createTransaction(request);

        verify(transactionRepository, times(1)).save(any());

        sender.setWallet(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setWallet(new BigDecimal(20));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "Transação realizada com sucesso.");
        verify(notificationService, times(1)).sendNotification(receiver, "Transação recebida com sucesso.");
    }

    @Test
    @DisplayName("Should throw Exception when Transaction is not allowed.")
    void createTransactionCase2() throws Exception {
        Long id1 = 1L;
        Long id2 = 2L;
        User sender = new User(id1, "Maria", "Sousa", "999999999901", "maria@gmail.com", "12345", UserType.CLIENTE, new BigDecimal(10));
        User receiver = new User(id2, "Eduarda", "Martins", "999999999902", "eduarda@gmail.com", "12345", UserType.CLIENTE, new BigDecimal(10));

        when(userService.findUserById(id1)).thenReturn(sender);
        when(userService.findUserById(id2)).thenReturn(receiver);

        when(authorizationService.authorizeTransaction()).thenReturn(false);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            TransactionDto request = new TransactionDto(new BigDecimal(10), id1, id2);
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transação não autorizada.", thrown.getMessage());

    }

}