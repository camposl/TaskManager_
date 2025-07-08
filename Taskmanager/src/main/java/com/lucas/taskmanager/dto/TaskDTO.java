package com.lucas.taskmanager.dto;

import com.lucas.taskmanager.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    public TaskDTO(Task entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        priority = entity.getPriority();
        status = entity.getStatus();
        deadLine = entity.getDeadLine();
    }
}
