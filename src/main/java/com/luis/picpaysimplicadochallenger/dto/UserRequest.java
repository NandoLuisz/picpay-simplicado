package com.luis.picpaysimplicadochallenger.dto;

import com.luis.picpaysimplicadochallenger.ultis.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "Nome é obrigaório.")
        String name,

        @NotBlank(message = "E-mail é obrigaório.")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Senha é obrigaório.")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
        String password,

        @NotBlank(message = "CPF/CNPJ é obrigatório")
        String codeId,

        @NotNull(message = "O tipo de usuário é obrigatório.")
        UserType usersType){
}
