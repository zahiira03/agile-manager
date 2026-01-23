package com.agilemanager.services.impl;

import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.Project;
import com.agilemanager.mappers.ProjectMapper;
import com.agilemanager.repository.ProductBacklogRepository;
import com.agilemanager.repository.ProjectRepository;
import com.agilemanager.services.interfaces.ProjetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ProjetServiceImpl implements ProjetService {

    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    private final ProductBacklogRepository productBacklogRepository;

    @Override
    public ProjectDto getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found: " + id));
        //Project project=projectRepository.findById(id).get();
        return projectMapper.toDto(project);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @Override
    public ProjectDto createProject(ProjectDto projectdto) {

        //Mapper DTO -> Entity (sans relations car ignore=true dans mapper)
        Project project = projectMapper.toEntity(projectdto);

        //Créer automatiquement un ProductBacklog
        ProductBacklog backlog = new ProductBacklog();
        backlog.setName("Product Backlog - " + project.getName());

        //Sauver le backlog pour obtenir son ID
        ProductBacklog savedBacklog = productBacklogRepository.save(backlog);

        //Lier le backlog au projet
        project.setProductBacklog(savedBacklog);

        //Sauver le projet
        Project savedProject = projectRepository.save(project);

        //Retourner DTO
        return projectMapper.toDto(savedProject);
    }
    @Override
    public ProjectDto updateProject(Long id, ProjectDto dto) {

        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found: " + id));

        existing.setName(dto.getName());
        existing.setProjectKey(dto.getProjectKey());
        Project saved = projectRepository.save(existing);
        return projectMapper.toDto(saved);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found: " + id));
        projectRepository.delete(project);
    }
}
