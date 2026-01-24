package com.agilemanager.mappers;

import com.agilemanager.Dtos.ProductBacklogDto;
import com.agilemanager.Dtos.UserStoryDto;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.Task;
import com.agilemanager.entities.UserStory;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserStoryMapper {
    @Mapping(source = "epic.id", target = "epicId")
    @Mapping(source = "sprint.id", target = "sprintId")
    @Mapping(source = "tasks", target = "tasksIds")
    UserStoryDto toDto(UserStory entity);


    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "sprint", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    UserStory toEntity(UserStoryDto dto);


    // Mapping personnalisé
    default Long map(Task task) {
        return task == null ? null : task.getId();
    }

    void updateEntityFromDto(UserStoryDto userStoryDto, @MappingTarget UserStory entity);
}
