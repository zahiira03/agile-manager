package com.agilemanager.services.interfaces;

import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.entities.Project;
import org.springframework.stereotype.Service;


@Service
public interface ProjetService {
    ProjectDto getProject(Long id);
    void setProject(Project project);
}
