package com.agilemanager.Dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductBacklogDto {
    private Long id;
    private String name;
    private List<Long> epicIds;   // ou userStoryIds selon ton modèle
}

