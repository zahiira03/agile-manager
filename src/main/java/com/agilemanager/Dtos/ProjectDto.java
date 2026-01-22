package com.agilemanager.Dtos;

import com.agilemanager.entities.Sprint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
public class ProjectDto {
    private Long id;
    private String name;
    private String projectKey;
    private LocalDateTime createdAt;
    private Long productBacklogId;
    private List<Long> sprintIds;


}
