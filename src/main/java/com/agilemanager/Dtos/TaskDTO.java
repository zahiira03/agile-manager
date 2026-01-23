package com.agilemanager.Dtos;

import com.agilemanager.entities.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Long user;
    private Long userStoryId;

}
