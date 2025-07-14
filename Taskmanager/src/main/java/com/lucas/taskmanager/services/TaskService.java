package com.lucas.taskmanager.services;

import com.lucas.taskmanager.dto.CategoryDTO;
import com.lucas.taskmanager.dto.TaskDTO;
import com.lucas.taskmanager.entities.Category;
import com.lucas.taskmanager.entities.Task;
import com.lucas.taskmanager.entities.User;
import com.lucas.taskmanager.repositories.TaskRepository;
import com.lucas.taskmanager.repositories.UserRepository;
import com.lucas.taskmanager.services.exceptions.DatabaseException;
import com.lucas.taskmanager.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private UserRepository userRepository;

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

    @Transactional
    public TaskDTO insert(TaskDTO dto){
        Task entity = new Task();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new TaskDTO(entity);
    }

    @Transactional
    public TaskDTO update(Long id, TaskDTO dto){
        try{
           Task entity = repository.getReferenceById(id);
           copyDtoToEntity(dto, entity);
           entity = repository.save(entity);
              return new TaskDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(TaskDTO dto, Task entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPriority(dto.getPriority());
        entity.setStatus(dto.getStatus());
        entity.setDeadLine(dto.getDeadLine());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        entity.setUser(user);

        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()){
            Category cat = new Category();
            cat.setId(catDto.getId());
            entity.getCategories().add(cat);
        }
    }

}
