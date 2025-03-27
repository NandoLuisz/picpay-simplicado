package com.luis.picpaysimplicadochallenger.repository;

import com.luis.picpaysimplicadochallenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserCodeId(String codeId);
}
