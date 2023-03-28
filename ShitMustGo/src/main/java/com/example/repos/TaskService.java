package com.example.repos;

import com.example.Account;
import com.example.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired TaskRepo taskRepo;

    public void addTask(Task task) {
    taskRepo.save(task);
    }
}
