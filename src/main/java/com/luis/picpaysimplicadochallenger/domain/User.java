package com.luis.picpaysimplicadochallenger.domain;

import com.luis.picpaysimplicadochallenger.dto.user.UserRequestDto;
import com.luis.picpaysimplicadochallenger.ultis.UserType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String document;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    private BigDecimal wallet;

    public User(UserRequestDto data) {
        this.firstname = data.fisrtname();
        this.lastname = data.lastname();
        this.email = data.email();
        this.password = data.password();
        this.document = data.document();
        this.userType = data.userType();
        this.wallet = data.wallet();
    }

    public User() {

    }

    public User(Long id, String firstname, String lastname, String email, String password, String document, UserType userType, BigDecimal wallet) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.document = document;
        this.userType = userType;
        this.wallet = wallet;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }
}
