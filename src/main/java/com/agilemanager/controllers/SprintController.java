package com.agilemanager.controllers;

import com.agilemanager.Dtos.SprintDTO;
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

    // CREATE Sprint
    @PostMapping
    public ResponseEntity<SprintDTO> create(@RequestBody SprintDTO dto) {
        SprintDTO created = sprintService.createSprint(dto);
        return ResponseEntity.status(201).body(created);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAll() {
        return ResponseEntity.ok(sprintService.getAllSprints());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SprintDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.getSprint(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SprintDTO> update(@PathVariable Long id, @RequestBody SprintDTO dto) {
        return ResponseEntity.ok(sprintService.updateSprint(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------------
    // Sprint Backlog Endpoints
    // -------------------------

    // GET Sprint Backlog (UserStories)
    @GetMapping("/{sprintId}/backlog")
    public ResponseEntity<List<UserStoryDto>> backlog(@PathVariable Long sprintId) {
        return ResponseEntity.ok(sprintService.getSprintBacklog(sprintId));
    }

    // ADD UserStory to Sprint
    @PostMapping("/{sprintId}/userStories/{userStoryId}")
    public ResponseEntity<UserStoryDto> addUserStory(@PathVariable Long sprintId,
                                                     @PathVariable Long userStoryId) {
        return ResponseEntity.ok(sprintService.addUserStoryToSprint(sprintId, userStoryId));
    }

    // REMOVE UserStory from Sprint
    @DeleteMapping("/{sprintId}/userStories/{userStoryId}")
    public ResponseEntity<UserStoryDto> removeUserStory(@PathVariable Long sprintId,
                                                        @PathVariable Long userStoryId) {
        return ResponseEntity.ok(sprintService.removeUserStoryFromSprint(sprintId, userStoryId));
    }
}
