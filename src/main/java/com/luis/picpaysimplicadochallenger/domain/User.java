package com.luis.picpaysimplicadochallenger.domain;

import com.luis.picpaysimplicadochallenger.ultis.UserType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String codeId;

    @Column(nullable = false, unique = true)
    private Integer codeTransfer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false)
    private Float wallet;

    public User(String name, String email, String password, String codeId, UserType userType, Integer codeTransfer) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.codeId = codeId;
        this.userType = userType;
        this.wallet = 0.0F;
        this.codeTransfer = codeTransfer;
    }

    public User() {
        this.wallet = 0.0F;
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

    public Integer getCodeTransfer() {
        return codeTransfer;
    }

    public void setCodeTransfer(Integer codeTransfer) {
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Float getWallet() {
        return wallet;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }
}
