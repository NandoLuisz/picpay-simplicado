package com.luis.picpaysimplicadochallenger.domain;

import com.luis.picpaysimplicadochallenger.ultis.UserType;
import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserType usersType;

    public Users(String name, String cpf, String email, String password, UserType usersType) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.usersType = usersType;
    }

    public Users() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUsersType() {
        return usersType;
    }

    public void setUsersType(UserType usersType) {
        this.usersType = usersType;
    }
}
