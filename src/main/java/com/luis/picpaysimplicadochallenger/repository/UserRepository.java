package com.luis.picpaysimplicadochallenger.repository;

import com.luis.picpaysimplicadochallenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);

}
