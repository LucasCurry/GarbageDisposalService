package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Service
@SessionScope
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "Fältet får ej vara tomt")
    String firstName;
    @NotEmpty(message = "Fältet får ej vara tomt")
    String lastName;
    @NotEmpty(message = "Fältet får ej vara tomt")
    String username;
    @Size(min= 3, message="Lösenord måste bestå av minst 3 tecken.")
    @NotEmpty(message = "Fältet får ej vara tomt")
    String password;
    @NotEmpty(message = "Fältet får ej vara tomt")
    String phoneNumber;
    @Email
    @NotEmpty(message = "Fältet får ej vara tomt")
    String email;
    @NotEmpty(message = "Fältet får ej vara tomt")
    String address;




    public Account() {
    }

    public Account(String firstName, String lastName, String username, String password, String phoneNumber, String email, String adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = adress;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '}';
    }
}
