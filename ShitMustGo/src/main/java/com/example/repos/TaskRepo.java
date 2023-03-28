package com.example.repos;

import com.example.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface TaskRepo extends CrudRepository<Task,Long> {
}
