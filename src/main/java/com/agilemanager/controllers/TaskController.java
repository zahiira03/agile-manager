package com.agilemanager.controllers;

import com.agilemanager.Dtos.TaskDTO;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO dto) {
        return ResponseEntity.status(201).body(taskService.createTask(dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDTO>> getByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }

    @GetMapping("/userStory/{userStoryId}")
    public ResponseEntity<List<TaskDTO>> getByUserStory(@PathVariable Long userStoryId) {
        return ResponseEntity.ok(taskService.getTasksByUserStory(userStoryId));
    }

    @GetMapping("/assignedUser/{userId}")
    public ResponseEntity<List<TaskDTO>> getByAssignedUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByAssignedUser(userId));
    }

    @PostMapping("/{taskId}/assign/userStory/{userStoryId}")
    public ResponseEntity<Void> assignToUserStory(@PathVariable Long taskId, @PathVariable Long userStoryId) {
        taskService.assignTaskToUserStory(taskId, userStoryId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{taskId}/assign/user/{userId}")
    public ResponseEntity<Void> assignToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok().build();
    }
}