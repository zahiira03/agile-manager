package com.agilemanager.controllers;

import com.agilemanager.entities.Task;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.services.interfaces.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/userstories/{userStoryId}/tasks")
    public Task create(@PathVariable Long userStoryId, @RequestBody Task task) {
        return taskService.createTask(userStoryId, task);
    }

    @GetMapping("/userstories/{userStoryId}/tasks")
    public List<Task> byUserStory(@PathVariable Long userStoryId) {
        return taskService.getTasksByUserStory(userStoryId);
    }

    @PutMapping("/tasks/{taskId}")
    public Task update(@PathVariable Long taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @PatchMapping("/tasks/{taskId}/status")
    public Task changeStatus(@PathVariable Long taskId, @RequestBody StatusRequest req) {
        return taskService.changeStatus(taskId, req.status);
    }

    @DeleteMapping("/tasks/{taskId}")
    public void delete(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    public static class StatusRequest {
        public Status status;
    }
}
