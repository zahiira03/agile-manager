package com.agilemanager.Dtos;


import com.agilemanager.entities.enums.SprintStatus;
import jdk.jshell.Snippet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter

public class SprintDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private SprintStatus status;

    // Sprint appartient à projet
    private Long projectId;

    // sprint contient les userstory
    private List<Long> userStoryIds;


}
