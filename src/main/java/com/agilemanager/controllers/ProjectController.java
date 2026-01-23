package com.agilemanager.controllers;

import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.services.interfaces.ProjetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjetService projetService;

    // GET /api/projects/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        ProjectDto project = projetService.getProject(id);
        return ResponseEntity.ok(project);
    }

    // GET /api/projects
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projetService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    // POST /api/projects
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto created = projetService.createProject(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    // PUT /api/projects/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectDto projectDto
    ) {
        ProjectDto updated = projetService.updateProject(id, projectDto);
        return ResponseEntity.ok(updated);
    }


    // DELETE /api/projects/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projetService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

