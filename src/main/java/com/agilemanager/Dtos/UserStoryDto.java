package com.agilemanager.Dtos;

import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStoryDto {

    private Long id;
    private String title;
    private String description;

    private MoSCoW priority;
    private Status status;

    // Relations (uniquement les IDs)
    private Long epicId;
    private Long sprintId;

    // Tasks associées
    private List<Long> tasksIds;
}
