package com.example;

import com.example.repos.AccountRepo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty (message = "Fält får inte vara tomt.")
    String title;
    @NotEmpty (message = "Fält får inte vara tomt.")
    String description;
    String address;
    String city;
    @NotEmpty(message = "Vänligen ange bildlänk.")
    String image;
    @NotNull(message = "Fält får inte vara tomt.")
    Integer price;
    Long accountId;
    Long bookedId;
    //@Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdAt;
    boolean isAccepted;

    public Task() {
    }

    public Task(String title, String address, String image, Integer price, String description, Long accountId, String city, LocalDateTime createdAt) {
        this.title = title;
        this.address = address;
        this.image = image;
        this.price = price;
        this.description = description;
        this.accountId = accountId;
        this.city = city;
        this.isAccepted = false;
        this.createdAt = createdAt;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBookedId() {
        return bookedId;
    }

    public void setBookedId(Long bookedId) {
        this.bookedId = bookedId;
    }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                '}';
    }


}
