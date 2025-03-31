package com.luis.picpaysimplicadochallenger.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UserWithdrawMoneyDto(
        @NotNull(message = "CPF/CNPJ é obrigatório")
        Long id,

        @Positive(message = "O valor deve ser maior que 0")
        BigDecimal value
) {
}
