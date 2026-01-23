package com.agilemanager.mappers;


import com.agilemanager.Dtos.ProductBacklogDto;
import com.agilemanager.Dtos.ProjectDto;
import com.agilemanager.entities.Epic;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//(ne pas écraser si null)
public interface ProductBacklogMapper {
    @Mapping(source = "epics", target = "epicIds")
    ProductBacklogDto toDto(ProductBacklog productbacklog);

    default Long map(Epic epic) {
        return epic == null ? null : epic.getId();
    }

    @Mapping(target = "epics", ignore = true)
    @Mapping(target = "project", ignore = true)
    ProductBacklog toEntity(ProductBacklogDto productBacklogDto);

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "epics", ignore = true)
    void updateEntityFromDto(ProductBacklogDto dto, @MappingTarget ProductBacklog entity);

}
