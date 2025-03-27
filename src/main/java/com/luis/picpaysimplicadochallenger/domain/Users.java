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
    private String codeId;

    @Column(nullable = false, unique = true)
    private String codeTransfer;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserType usersType;

    @Column(nullable = false)
    private Float wallet;

    public Users(String name, String codeId, String codeTransfer, String email, String password, UserType usersType, Float wallet) {
        this.name = name;
        this.codeId = codeId;
        this.codeTransfer = codeTransfer;
        this.email = email;
        this.password = password;
        this.usersType = usersType;
        this.wallet = wallet;
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

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeTransfer() {
        return codeTransfer;
    }

    public void setCodeTransfer(String codeTransfer) {
        this.codeTransfer = codeTransfer;
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

    public Float getWallet() {
        return wallet;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }
}
