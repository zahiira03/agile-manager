package com.agilemanager.controllers;

import com.agilemanager.entities.Sprint;
import com.agilemanager.entities.UserStory;
import com.agilemanager.services.interfaces.SprintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping
    public Sprint create(@RequestBody Sprint sprint) {
        return sprintService.createSprint(sprint);
    }

    @GetMapping
    public List<Sprint> all() {
        return sprintService.getAllSprints();
    }

    @GetMapping("/{id}")
    public Sprint one(@PathVariable Long id) {
        return sprintService.getSprint(id);
    }

    @GetMapping("/{id}/backlog")
    public List<UserStory> sprintBacklog(@PathVariable Long id) {
        return sprintService.getSprintBacklog(id);
    }

    @PostMapping("/{sprintId}/userstories/{userStoryId}")
    public UserStory addUserStory(@PathVariable Long sprintId, @PathVariable Long userStoryId) {
        return sprintService.addUserStoryToSprint(sprintId, userStoryId);
    }

    @DeleteMapping("/{sprintId}/userstories/{userStoryId}")
    public UserStory removeUserStory(@PathVariable Long sprintId, @PathVariable Long userStoryId) {
        return sprintService.removeUserStoryFromSprint(sprintId, userStoryId);
    }
}
