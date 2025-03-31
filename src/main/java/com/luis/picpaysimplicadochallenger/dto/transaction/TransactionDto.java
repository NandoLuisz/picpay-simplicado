package com.luis.picpaysimplicadochallenger.dto.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionDto(
    @Positive(message = "o valor tem que ser maior que zero.")
    BigDecimal value,

    @NotNull(message = "Remetente é obrigatório.")
    Long senderId,

    @NotNull(message = "Destinatário é obrigatório.")
    Long receiverId
        ){
}
