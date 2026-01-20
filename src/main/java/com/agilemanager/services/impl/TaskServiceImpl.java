package com.agilemanager.services.impl;

import com.agilemanager.entities.Task;
import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.repository.TaskRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserStoryRepository userStoryRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           UserStoryRepository userStoryRepository) {
        this.taskRepository = taskRepository;
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public Task createTask(Long userStoryId, Task task) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("UserStory not found"));

        task.setUserStory(userStory);
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO); // adapte selon ton enum
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByUserStory(Long userStoryId) {
        return taskRepository.findByUserStoryId(userStoryId);
    }

    @Override
    public Task updateTask(Long taskId, Task data) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(data.getTitle());
        task.setDescription(data.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Long taskId, Status status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
