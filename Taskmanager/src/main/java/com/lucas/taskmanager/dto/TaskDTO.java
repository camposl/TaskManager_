package com.lucas.taskmanager.dto;

import com.lucas.taskmanager.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private  String priority;
    private String status;
    private LocalDate deadLine;
    private Long userId;
    private String userName;
    private Set<CategoryDTO> categories;

    public TaskDTO(Task entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        priority = entity.getPriority();
        status = entity.getStatus();
        deadLine = entity.getDeadLine();
        userId = entity.getUser().getId();
        userName = entity.getUser().getName();
        categories = entity.getCategories().stream()
                .map(CategoryDTO::new)
                .collect(java.util.stream.Collectors.toSet());

    }
}
