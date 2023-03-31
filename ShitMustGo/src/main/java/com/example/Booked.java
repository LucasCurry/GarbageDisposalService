package com.example;

import com.example.repos.BookedRepo;
import com.example.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class Booked {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    BookedRepo bookedRepo;

}
