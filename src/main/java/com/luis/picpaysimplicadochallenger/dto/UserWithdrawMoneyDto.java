package com.luis.picpaysimplicadochallenger.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UserWithdrawMoneyDto(
        @NotBlank(message = "CPF/CNPJ é obrigatório")
        String codeId,

        @Positive(message = "O valor deve ser maior que 0")
        Float value
) {
}
