package com.agilemanager.mappers;

import com.agilemanager.Dtos.SprintDTO;
import com.agilemanager.entities.Sprint;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SprintMapper {

    @Mapping(source = "project.id", target = "projectId")
    SprintDTO toDto(Sprint sprint);

    @Mapping(target = "project", ignore = true)      //  service
    @Mapping(target = "userStories", ignore = true)  // user stories  mapping
    Sprint toEntity(SprintDTO dto);

    List<SprintDTO> toDtoList(List<Sprint> sprints);

    // update entity from dto
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "userStories", ignore = true)
    void updateEntityFromDto(SprintDTO dto, @MappingTarget Sprint sprint);
}
