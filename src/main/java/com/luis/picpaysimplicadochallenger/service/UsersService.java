package com.luis.picpaysimplicadochallenger.service;

import com.luis.picpaysimplicadochallenger.dto.UserResponse;
import com.luis.picpaysimplicadochallenger.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<UserResponse> getAllUsers(){
        return this.usersRepository.findAll().stream().map(UserResponse::new).toList();
    }
}
