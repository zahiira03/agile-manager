package com.agilemanager.services.interfaces;

import com.agilemanager.entities.Task;
import com.agilemanager.entities.enums.Status;

import java.util.List;

public interface TaskService {

    Task createTask(Long userStoryId, Task task);

    List<Task> getTasksByUserStory(Long userStoryId);

    Task updateTask(Long taskId, Task task);

    Task changeStatus(Long taskId, Status status);

    void deleteTask(Long taskId);
}
