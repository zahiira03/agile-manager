package com.agilemanager.mappers;

import com.agilemanager.Dtos.EpicDto;
import com.agilemanager.Dtos.ProductBacklogDto;
import com.agilemanager.entities.Epic;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.UserStory;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface EpicMapper {
    @Mapping(source = "productBacklog.id" ,target="productBacklogId")
    @Mapping(source = "userStories", target = "userStoryIds")
    EpicDto toEpicDto(Epic epic);



    @Mapping(target = "productBacklog", ignore = true)
    @Mapping(target = "userStories", ignore = true)
    Epic toEntity(EpicDto epicDto);

    @Mapping(target = "productBacklog", ignore = true)
    @Mapping(target = "userStories", ignore = true)
    void updateEntityFromDto(EpicDto epicdto, @MappingTarget Epic entity);


    default Long map(UserStory userstory) {
        return userstory == null ? null : userstory.getId();
    }

    default Long map(ProductBacklog backlog) {
        return backlog == null ? null : backlog.getId();
    }
}
