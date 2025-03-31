package com.luis.picpaysimplicadochallenger.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UserDepositMoneyDto(
        @NotBlank(message = "CPF/CNPJ é obrigatório")
        String codeId,

        @Positive(message = "O valor deve ser maior que 0")
        Float value
        ) {
}
