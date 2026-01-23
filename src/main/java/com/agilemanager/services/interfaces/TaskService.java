package com.agilemanager.services.interfaces;

import com.agilemanager.Dtos.TaskDTO;
import com.agilemanager.entities.enums.Status;

import java.util.List;

public interface TaskService {

    // CRUD Task (DTO)
    TaskDTO createTask(TaskDTO dto);
    List<TaskDTO> getAllTasks();
    TaskDTO getTask(Long id);
    TaskDTO updateTask(Long id, TaskDTO dto);
    void deleteTask(Long id);
    List<TaskDTO> getTasksByStatus(Status status);
    List<TaskDTO> getTasksByAssignedUser(Long userId);

    List<TaskDTO> getTasksByUserStory(Long userStoryId);

    void assignTaskToUserStory(Long taskId, Long userStoryId);
    void assignTaskToUser(Long taskId, Long userId);
}
