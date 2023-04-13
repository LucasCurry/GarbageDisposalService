package com.example.repos;

import com.example.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    List<Task> tasks;

    public String addTask(@Valid Task task, BindingResult br, RedirectAttributes ra) {
        if(task.getPrice() == null){
            br.rejectValue("price", "error", "Sätt ett pris.");
            ra.addFlashAttribute("price", "Sätt ett pris.");
            return "createTask";
        }
        if (br.hasErrors()){
            ra.addFlashAttribute("FailedSignup", "Något blev fel, försök igen");
            return "createTask";
        }
        taskRepo.save(task);
        return "home2";
    }

    public List<Task> sortList(String city, String sort, int page, int pageSize) {
        List<Task> tasks = getPage(page, pageSize);
        if (city.equals("") && sort.equals("")) {
            return tasks;
        } else if (sort.equals("")) {
            tasks = taskRepo.findAllByCity(city);
            return getPage(page, pageSize, tasks);
        } else if (city.equals("")) {
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
        } else {
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
        int from = Math.max(0, page * pageSize);
        int to = Math.min(tasks.size(), (page + 1) * pageSize);

        return tasks.subList(from, to);
    }

    public List<Task> getPage(int page, int pageSize, List<Task> tasks) {
        int from = Math.max(0, page * pageSize);
        int to = Math.min(tasks.size(), (page + 1) * pageSize);

        return tasks.subList(from, to);
    }

    public int numberOfPages(int pageSize, List<Task> tasks) {
        return (int) Math.ceil((double) tasks.size() / pageSize);
    }
    public int numberOfPages(int pageSize) {
        return (int) Math.ceil((double) tasks.size() / pageSize);
    }
}
