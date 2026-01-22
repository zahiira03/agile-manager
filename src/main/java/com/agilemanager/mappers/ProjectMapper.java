package com.agilemanager.mappers;

import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "productBacklog.id", target = "productBacklogId")
    @Mapping(target = "sprintIds",ignore = true)
    ProjectDto toDto(Project project);

    @Mapping(target = "sprints", ignore = true)
    Project toEntity(ProjectDto projectDto);
}
