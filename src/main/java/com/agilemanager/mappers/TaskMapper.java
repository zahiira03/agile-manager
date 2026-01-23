package com.agilemanager.mappers;

import com.agilemanager.Dtos.TaskDTO;
import com.agilemanager.entities.Task;
import com.agilemanager.entities.User;
import com.agilemanager.entities.UserStory;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskDTO dto, UserStory userStory, User assignedUser) {
        if (dto == null) return null;

        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .userStory(userStory)
                .assignedUser(assignedUser)
                .build();
    }

    public void updateEntity(Task task, TaskDTO dto, UserStory userStory, User assignedUser) {
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setUserStory(userStory);
        task.setAssignedUser(assignedUser);
    }

    public TaskDTO toDto(Task task) {
        if (task == null) return null;

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());

        dto.setUserStoryId(task.getUserStory() != null ? task.getUserStory().getId() : null);
        dto.setUser(task.getAssignedUser() != null ? task.getAssignedUser().getId() : null);

        return dto;
    }
}
