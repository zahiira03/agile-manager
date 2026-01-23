package com.agilemanager.mappers;

import com.agilemanager.Dtos.TaskDTO;
import com.agilemanager.entities.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "userStory.id", target = "userStoryId")
    @Mapping(source = "assignedUser.id", target = "assignedUserId")
    TaskDTO toDto(Task task);

    @Mapping(target = "userStory", ignore = true)
    @Mapping(target = "assignedUser", ignore = true)
    Task toEntity(TaskDTO dto);

    List<TaskDTO> toDtoList(List<Task> tasks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userStory", ignore = true)
    @Mapping(target = "assignedUser", ignore = true)
    void updateEntityFromDto(TaskDTO dto, @MappingTarget Task task);
}
