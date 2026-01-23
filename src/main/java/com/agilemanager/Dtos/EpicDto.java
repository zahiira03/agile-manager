package com.agilemanager.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EpicDto {
    private Long id;
    private String title;
    private String description;
    private Long productBacklogId;
    private List<Long> userStoryIds;
}
