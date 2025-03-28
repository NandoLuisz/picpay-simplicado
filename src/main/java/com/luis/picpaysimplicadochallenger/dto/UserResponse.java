package com.luis.picpaysimplicadochallenger.dto;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.ultis.UserType;


public record UserResponse(
        String name,
        String email,
        String codeId,
        Integer codeTransfer,
        UserType userType,
        Float wallet) {
    public UserResponse(User user){
        this(user.getName(), user.getEmail(), user.getCodeId(), user.getCodeTransfer(), user.getUserType(), user.getWallet());
    }
}
