package com.luis.picpaysimplicadochallenger.controller;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.user.UserRequestDto;
import com.luis.picpaysimplicadochallenger.dto.user.UserResponseDto;
import com.luis.picpaysimplicadochallenger.dto.user.UserWithdrawMoneyDto;
import com.luis.picpaysimplicadochallenger.repository.UserRepository;
import com.luis.picpaysimplicadochallenger.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

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

    @PutMapping("/withdraw-money-from-the-wallet")
    public ResponseEntity<String> withdrawMoneyFromTheWallet(@Valid @RequestBody UserWithdrawMoneyDto userWithdrawMoneyDto){
        Optional<User> userExist = this.userRepository.findUserById(userWithdrawMoneyDto.id());
        if(userExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o CPF/CNPJ" + userWithdrawMoneyDto.id() + "não encontrado.");
        }

        User user = userExist.get();
        if(userWithdrawMoneyDto.value().compareTo(user.getWallet()) > 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor da retirada maior doque é possível.");
        }
        user.setWallet(user.getWallet().subtract(userWithdrawMoneyDto.value()));
        this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Valor retirado com sucesso.");
    }


}
