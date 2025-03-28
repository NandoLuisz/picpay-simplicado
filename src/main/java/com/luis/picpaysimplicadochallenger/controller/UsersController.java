package com.luis.picpaysimplicadochallenger.controller;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.UserDepositMoneyDto;
import com.luis.picpaysimplicadochallenger.dto.UserRequest;
import com.luis.picpaysimplicadochallenger.dto.UserResponse;
import com.luis.picpaysimplicadochallenger.dto.UserWithdrawMoneyDto;
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

    @PutMapping("/deposit-money-in-the-wallet")
    public ResponseEntity<String> depositMoneyInTheWallet(@Valid @RequestBody UserDepositMoneyDto userInsertMoneyDto){
        Optional<User> userExist = this.usersRepository.findUserByCodeId(userInsertMoneyDto.codeId());
        if(userExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o CPF/CNPJ" + userInsertMoneyDto.codeId() + "não encontrado.");
        }

        User user = userExist.get();
        user.setWallet(user.getWallet() + userInsertMoneyDto.value());
        this.usersRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Valor inserido com sucesso.");
    }

    @PutMapping("/withdraw-money-from-the-wallet")
    public ResponseEntity<String> withdrawMoneyFromTheWallet(@Valid @RequestBody UserWithdrawMoneyDto userWithdrawMoneyDto){
        Optional<User> userExist = this.usersRepository.findUserByCodeId(userWithdrawMoneyDto.codeId());
        if(userExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o CPF/CNPJ" + userWithdrawMoneyDto.codeId() + "não encontrado.");
        }

        User user = userExist.get();
        if(userWithdrawMoneyDto.value() > user.getWallet()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor da retirada maior doque é possível.");
        }
        user.setWallet(user.getWallet() - userWithdrawMoneyDto.value());
        this.usersRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Valor retirado com sucesso.");
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> listUsers = this.usersService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }
}
