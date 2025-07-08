package com.lucas.taskmanager.controllers;

import com.lucas.taskmanager.dto.TaskDTO;
import com.lucas.taskmanager.entities.Task;
import com.lucas.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> findByID(@PathVariable Long id){
        TaskDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findByTitle(
            @RequestParam(value = "title", defaultValue = "") String title){
        List<TaskDTO> list = service.findByTitle(title);
        return ResponseEntity.ok(list);
    }

}
