package com.agilemanager.services.impl;

import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.entities.Project;
import com.agilemanager.entities.Sprint;
import com.agilemanager.mappers.ProjectMapper;
import com.agilemanager.repository.ProjectRepository;
import com.agilemanager.services.interfaces.ProjetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProjetServiceImpl implements ProjetService {
    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    @Override
    public ProjectDto getProject(Long id) {
        Project project=projectRepository.findById(id).get();
        ProjectDto projectDto=projectMapper.toDto(project);
        List<Long> sprintIds=new ArrayList<>();
        for (Sprint sprint : project.getSprints()) {
            sprintIds.add(sprint.getId());
        }
        projectDto.setSprintIds(sprintIds);
        return projectDto;
    }

    @Override
    public void setProject(Project project) {

    }
}
