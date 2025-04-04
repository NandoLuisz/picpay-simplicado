package com.luis.picpaysimplicadochallenger.dto.user;

import com.luis.picpaysimplicadochallenger.ultis.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UserRequestDto(
        String fisrtname,
        String lastname,
        String email,
        String password,
        String document,
        BigDecimal wallet,
        UserType userType
        ){
}
