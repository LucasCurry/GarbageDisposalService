package com.example.repos;

import com.example.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task,Long> {
}
