package com.luis.picpaysimplicadochallenger.dto.user;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.ultis.UserType;

import java.math.BigDecimal;


public record UserResponseDto(
        String firstname,
        String lastname,
        String email,
        String document,
        UserType userType,
        BigDecimal wallet) {
    public UserResponseDto(User user){
        this(user.getFirstname(), user.getLastname(), user.getEmail(), user.getDocument(), user.getUserType(), user.getWallet());
    }
}
