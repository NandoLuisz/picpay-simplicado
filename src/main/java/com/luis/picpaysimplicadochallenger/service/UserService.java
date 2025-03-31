package com.luis.picpaysimplicadochallenger.service;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.user.UserRequestDto;
import com.luis.picpaysimplicadochallenger.dto.user.UserResponseDto;
import com.luis.picpaysimplicadochallenger.repository.UserRepository;
import com.luis.picpaysimplicadochallenger.ultis.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransation(User sender, BigDecimal value) throws Exception {
        if(sender.getUserType() == UserType.LOJISTA){
            throw new Exception("Usuário do tipo lojista não pode fazer transação.");
        }
        if(sender.getWallet().compareTo(value) < 0){
            throw new Exception("Valor da transisição é maior que a carteira do usuário.");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public User createUser(UserRequestDto data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public List<UserResponseDto> getAllUsers(){
        return this.userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }


}
