package com.agilemanager.mappers;

import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.entities.Project;
import com.agilemanager.entities.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "productBacklog.id", target = "productBacklogId")
    @Mapping(source = "sprints", target = "sprintIds") //    ici on mappe la liste
    ProjectDto toDto(Project project);
    // MapStruct saura faire List<Sprint> -> List<Long> grâce à cette méthode
    default Long map(Sprint sprint) {
        return sprint == null ? null : sprint.getId();
    }

    @Mapping(target = "productBacklog", ignore = true)
    @Mapping(target = "sprints", ignore = true)
    Project toEntity(ProjectDto projectDto);
}
