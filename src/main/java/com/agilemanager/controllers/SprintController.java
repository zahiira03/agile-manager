package com.agilemanager.controllers;

import com.agilemanager.Dtos.SprintDTO;
import com.agilemanager.Dtos.UserStoryDto;
import com.agilemanager.Dtos.UserStoryDto;
import com.agilemanager.services.interfaces.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    // Create sprint (requires projectId)
    @PostMapping
    public ResponseEntity<SprintDTO> create(@RequestBody SprintDTO dto) {
        return ResponseEntity.status(201).body(sprintService.createSprint(dto));
    }

    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAll() {
        return ResponseEntity.ok(sprintService.getAllSprints());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.getSprint(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintDTO> update(@PathVariable Long id, @RequestBody SprintDTO dto) {
        return ResponseEntity.ok(sprintService.updateSprint(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }

    // Get sprints by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<SprintDTO>> getByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(sprintService.getSprintsByProject(projectId));
    }

    // Assign userStory to sprint
    @PostMapping("/{sprintId}/userStories/{userStoryId}")
    public ResponseEntity<Void> assignUserStory(
            @PathVariable Long sprintId,
            @PathVariable Long userStoryId
    ) {
        sprintService.assignUserStoryToSprint(sprintId, userStoryId);
        return ResponseEntity.ok().build();
    }

    // Get userStories of a sprint
    @GetMapping("/{sprintId}/userStories")
    public ResponseEntity<List<UserStoryDto>> getUserStories(@PathVariable Long sprintId) {
        return ResponseEntity.ok(sprintService.getUserStoriesBySprint(sprintId));
    }
}
