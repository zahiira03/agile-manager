package com.agilemanager.Dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductBacklogDto {
    private Long id;
    private String name;
    private String description;

    private long projectId;
    private List<Long> epicIds;
}

