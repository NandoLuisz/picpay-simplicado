package com.luis.picpaysimplicadochallenger.controller;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.user.UserRequestDto;
import com.luis.picpaysimplicadochallenger.dto.user.UserResponseDto;
import com.luis.picpaysimplicadochallenger.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService usesService;

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequestDto data){
        User newUser = usesService.createUser(data);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> listUsers = this.usesService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }


}
