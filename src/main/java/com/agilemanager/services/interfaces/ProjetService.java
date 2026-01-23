package com.agilemanager.services.interfaces;

import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.entities.Project;
import org.springframework.stereotype.Service;
import java.util.List;



public interface ProjetService {
    ProjectDto getProject(Long id);
    List<ProjectDto> getAllProjects();
    ProjectDto createProject(ProjectDto project);
    ProjectDto updateProject(Long id,ProjectDto dto);
    void deleteProject(Long id);
}
