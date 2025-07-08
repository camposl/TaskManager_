package com.lucas.taskmanager.services;

import com.lucas.taskmanager.dto.TaskDTO;
import com.lucas.taskmanager.entities.Task;
import com.lucas.taskmanager.repositories.TaskRepository;
import com.lucas.taskmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Transactional(readOnly = true)
    public TaskDTO findById(Long id){
        Task task = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new TaskDTO(task);
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> findByTitle(String title){
        List<Task> list = repository.findByTitleContainingIgnoreCase(title);
        return list.stream().map(TaskDTO::new).collect(Collectors.toList());
    }

}
