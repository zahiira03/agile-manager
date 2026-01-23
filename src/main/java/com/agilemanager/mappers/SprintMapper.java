package com.agilemanager.mappers;

import com.agilemanager.Dtos.SprintDTO;
import com.agilemanager.entities.Project;
import com.agilemanager.entities.Sprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    public Sprint toEntity(SprintDTO dto, Project project) {
        if (dto == null) return null;

        return Sprint.builder()
                // id ما كنحطّوهش ف create (DB كتولد)
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus())
                .project(project)
                .build();
    }

    public void updateEntity(Sprint sprint, SprintDTO dto, Project project) {
        // update: كنبدلو الحقول
        sprint.setName(dto.getName());
        sprint.setStartDate(dto.getStartDate());
        sprint.setEndDate(dto.getEndDate());
        sprint.setStatus(dto.getStatus());
        sprint.setProject(project);
    }

    public SprintDTO toDto(Sprint sprint) {
        if (sprint == null) return null;

        SprintDTO dto = new SprintDTO();
        dto.setId(sprint.getId());
        dto.setName(sprint.getName());
        dto.setStartDate(sprint.getStartDate());
        dto.setEndDate(sprint.getEndDate());
        dto.setStatus(sprint.getStatus());

        // علاقة Sprint مع Project
        dto.setProjectId(sprint.getProject() != null ? sprint.getProject().getId() : null);

        return dto;
    }
}
