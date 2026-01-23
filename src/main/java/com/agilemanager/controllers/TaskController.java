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

    // CREATE
    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO dto) {
        TaskDTO created = taskService.createTask(dto);
        return ResponseEntity.status(201).body(created);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // GET BY STATUS

    @GetMapping("/status")
    public ResponseEntity<List<TaskDTO>> getByStatus(@RequestParam("value") Status status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }

    // GET TASKS BY USER STORY
    @GetMapping("/userStory/{userStoryId}")
    public ResponseEntity<List<TaskDTO>> getByUserStory(@PathVariable Long userStoryId) {
        return ResponseEntity.ok(taskService.getTasksByUserStory(userStoryId));
    }

    // ASSIGN TASK TO USER STORY
    @PutMapping("/{taskId}/assign/userStory/{userStoryId}")
    public ResponseEntity<TaskDTO> assignToUserStory(@PathVariable Long taskId,
                                                     @PathVariable Long userStoryId) {
        return ResponseEntity.ok(taskService.assignTaskToUserStory(taskId, userStoryId));
    }

    // UNASSIGN TASK FROM USER STORY
    @PutMapping("/{taskId}/unassign/userStory")
    public ResponseEntity<Void> unassignFromUserStory(@PathVariable Long taskId) {
        taskService.unassignTaskFromUserStory(taskId);
        return ResponseEntity.noContent().build();
    }
}
