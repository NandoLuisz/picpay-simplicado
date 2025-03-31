package com.luis.picpaysimplicadochallenger.dto.user;

import com.luis.picpaysimplicadochallenger.ultis.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "Primeiro nome é obrigaório.")
        String fisrtname,

        @NotBlank(message = "Último nome é obrigaório.")
        String lastname,

        @NotBlank(message = "E-mail é obrigaório.")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Senha é obrigaório.")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
        String password,

        @NotBlank(message = "CPF/CNPJ é obrigatório")
        String document,

        @NotNull(message = "O tipo de usuário é obrigatório.")
        UserType userType){
}
