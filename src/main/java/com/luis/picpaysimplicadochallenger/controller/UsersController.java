package com.luis.picpaysimplicadochallenger.controller;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.UserRequest;
import com.luis.picpaysimplicadochallenger.dto.UserResponse;
import com.luis.picpaysimplicadochallenger.repository.UsersRepository;
import com.luis.picpaysimplicadochallenger.service.UsersService;
import com.luis.picpaysimplicadochallenger.ultis.RandomCode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest userRequest){
        Optional<User> userByEmail = this.usersRepository.findUserByEmail(userRequest.email());
        if (userByEmail.isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        Optional<User> userByCodeId = this.usersRepository.findUserByCodeId(userRequest.codeId());
        if (userByCodeId.isPresent()) {
            return ResponseEntity.badRequest().body("CPF/CNPJ já cadastrado.");
        }

        RandomCode randomCode = new RandomCode();
        int randomCodeTransfer;

        do {
            randomCodeTransfer = randomCode.randomCodeGenerated();
        } while (this.usersRepository.findUserByCodeTransfer(randomCodeTransfer).isPresent());

        User newUser = new User(userRequest.name(),
                                userRequest.email(),
                                userRequest.password(),
                                userRequest.codeId(),
                                userRequest.userType(),
                                randomCodeTransfer
                );
        usersRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso.");
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> listUsers = this.usersService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }
}
