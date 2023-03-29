package com.example.repos;

import com.example.Account;
import com.example.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    @Autowired TaskRepo taskRepo;

    public void addTask(Task task) {
    taskRepo.save(task);
    }

    public List<Task> sortList(String city, String sort) {
        List<Task> tasks = (List<Task>) taskRepo.findAll();
        if(city.equals("") && sort.equals("")){
            return tasks;
        } else if(sort.equals("")) {
            tasks = taskRepo.findAllByCity(city);
            return tasks;
        }else if (city.equals("")){
        switch (sort) {
            case "HighPrice" -> {
                tasks = taskRepo.findAllByOrderByPriceDesc();
                return tasks;
            }
            case "LowPrice" -> {
                tasks = taskRepo.findAllByOrderByPrice();
                return tasks;
            }
        }
        }else if (city.equals("Stockholm")){
            switch (sort) {
                case "HighPrice" -> {
                    tasks = taskRepo.findByCityOrderByPriceDesc(city);
                    return tasks;
                }
                case "LowPrice" -> {
                    tasks = taskRepo.findByCityOrderByPrice(city);
                    return tasks;
                }
            }
        }else if (city.equals("Göteborg")){
            switch (sort) {
                case "HighPrice" -> {
                    tasks = taskRepo.findByCityOrderByPriceDesc(city);
                    return tasks;
                }
                case "LowPrice" -> {
                    tasks = taskRepo.findByCityOrderByPrice(city);
                    return tasks;
                }
            }
        }else if (city.equals("Malmö")){
            switch (sort) {
                case "HighPrice" -> {
                    tasks = taskRepo.findByCityOrderByPriceDesc(city);
                    return tasks;
                }
                case "LowPrice" -> {
                    tasks = taskRepo.findByCityOrderByPrice(city);
                    return tasks;
                }
            }
        }
        return tasks;
    }
}
