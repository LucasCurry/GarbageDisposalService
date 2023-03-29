package com.example.repos;

import com.example.Account;
import com.example.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;

@Service
public class TaskService {

    @Autowired TaskRepo taskRepo;

    public void addTask(Task task) {
    taskRepo.save(task);
    }

    public Task cityModelGeneration(String city) {
        if(Objects.equals(city, "")) {
            return (Task) taskRepo.findAll();
        } else return (Task) taskRepo.findAllByCity(city);
    }
}
