package com.agilemanager.services.interfaces;

import com.agilemanager.Dtos.SprintDTO;
import com.agilemanager.Dtos.UserStoryDto;

import java.util.List;

public interface SprintService {

    // CRUD Sprint (DTO)
    SprintDTO createSprint(SprintDTO dto);
    List<SprintDTO> getAllSprints();
    SprintDTO getSprint(Long id);

    SprintDTO getSprintById(Long id);

    SprintDTO updateSprint(Long id, SprintDTO sprintDTO);

    void deleteSprint(Long id);

    // Sprint Backlog (UserStories)
    List<UserStoryDto> getSprintBacklog(Long sprintId);
    UserStoryDto addUserStoryToSprint(Long sprintId, Long userStoryId);
    UserStoryDto removeUserStoryFromSprint(Long sprintId, Long userStoryId);
}
