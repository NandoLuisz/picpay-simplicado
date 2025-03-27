package com.luis.picpaysimplicadochallenger.controller;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.UserRequest;
import com.luis.picpaysimplicadochallenger.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest){
        Optional<User> userByEmail = this.usersRepository.findUserByEmail(userRequest.email());
        if (userByEmail.isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        Optional<User> userByCodeId = this.usersRepository.findUserCodeId(userRequest.codeId());
        if (userByCodeId.isPresent()) {
            return ResponseEntity.badRequest().body("CPF/CNPJ já cadastrado.");
        }

        User newUser = new User(userRequest.name(),
                                userRequest.email(),
                                userRequest.password(),
                                userRequest.codeId(),
                                userRequest.usersType());
        return ResponseEntity.ok(this.usersRepository.save(newUser));
    }
}
