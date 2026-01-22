package com.agilemanager.controllers;


import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.services.interfaces.ProjetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private ProjetService projetService;

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable Long id) {
        return projetService.getProject(id);
    }

}
