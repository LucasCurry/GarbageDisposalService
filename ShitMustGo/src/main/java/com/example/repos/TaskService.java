package com.example.repos;

import com.example.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;
    private List<Task> tasks;
    public void addTask(Task task) {
    taskRepo.save(task);
    }

    public List<Task> sortList(String city, String sort, int page) {
        int pageSize = 12;
        List<Task> tasks = getPage(page, pageSize);
        if(city.equals("") && sort.equals("")){
            return tasks;
        } else if(sort.equals("")) {
            tasks = taskRepo.findAllByCity(city);
            return getPage(page, pageSize, tasks);
        } else if (city.equals("")){
        switch (sort) {
            case "HighPrice" -> {
                tasks = taskRepo.findAllByOrderByPriceDesc();
                return getPage(page, pageSize, tasks);
            }
            case "LowPrice" -> {
                tasks = taskRepo.findAllByOrderByPrice();
                return getPage(page, pageSize, tasks);
            }
            case "Recent" -> {
                tasks = taskRepo.findAllByOrderByCreatedAtDesc();
                return getPage(page, pageSize, tasks);
            }
        }
        } else if (city.equals("Stockholm")){
            switch (sort) {
                case "HighPrice" -> {
                    tasks = taskRepo.findByCityOrderByPriceDesc(city);
                    return getPage(page, pageSize, tasks);
                }
                case "LowPrice" -> {
                    tasks = taskRepo.findByCityOrderByPrice(city);
                    return getPage(page, pageSize, tasks);
                }
                case "Recent" -> {
                    tasks = taskRepo.findByCityOrderByCreatedAtDesc(city);
                    return getPage(page, pageSize, tasks);
                }
            }
        }else if (city.equals("Göteborg")){
            switch (sort) {
                case "HighPrice" -> {
                    tasks = taskRepo.findByCityOrderByPriceDesc(city);
                    return getPage(page, pageSize, tasks);
                }
                case "LowPrice" -> {
                    tasks = taskRepo.findByCityOrderByPrice(city);
                    return getPage(page, pageSize, tasks);
                }
                case "Recent" -> {
                    tasks = taskRepo.findByCityOrderByCreatedAtDesc(city);
                    return getPage(page, pageSize, tasks);
                }
            }
        }else if (city.equals("Malmö")){
            switch (sort) {
                case "HighPrice" -> {
                    tasks = taskRepo.findByCityOrderByPriceDesc(city);
                    return getPage(page, pageSize, tasks);
                }
                case "LowPrice" -> {
                    tasks = taskRepo.findByCityOrderByPrice(city);
                    return getPage(page, pageSize, tasks);
                }
                case "Recent" -> {
                    tasks = taskRepo.findByCityOrderByCreatedAtDesc(city);
                    return getPage(page, pageSize, tasks);
                }
            }
        }
        return tasks;
    }

    public List<Task> getPage(int page, int pageSize) {
        tasks = taskRepo.findAllByBookedId(null);
        int from = Math.max(0,page*pageSize);
        int to = Math.min(tasks.size(),(page+1)*pageSize);

        return tasks.subList(from, to);
    }

    public List<Task> getPage(int page, int pageSize, List<Task> tasks) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(tasks.size(),(page+1)*pageSize);

        return tasks.subList(from, to);
    }

    public int numberOfPages(int pageSize) {
        return (int)Math.ceil((double)tasks.size() / pageSize);
    }
}
