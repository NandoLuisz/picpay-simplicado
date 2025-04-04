package com.luis.picpaysimplicadochallenger.repository;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.user.UserRequestDto;
import com.luis.picpaysimplicadochallenger.ultis.UserType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findUserByDocumentCase1() {
        String document = "99999999999";

        UserRequestDto newUser = new UserRequestDto(
                "Luis",
                "Fernando",
                "nando@gmail.com",
                "123456", document,
                new BigDecimal(10),
                UserType.CLIENTE
                );

        this.createUser(newUser);

        Optional<User> result =  this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when user not exists")
    void findUserByDocumentCase2() {
        String document = "99999999999";

        Optional<User> result =  this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserRequestDto userRequestDto){
        User newUser = new User(userRequestDto);
        this.entityManager.persist(newUser);
        return newUser;
    }
}