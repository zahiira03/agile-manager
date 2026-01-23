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

    List<SprintDTO> getSprintsByProject(Long projectId);

    // Assign user story to sprint
    void assignUserStoryToSprint(Long sprintId, Long userStoryId);

    // Get userStories of sprint (إلا بغيتهم DTO)
    List<UserStoryDto> getUserStoriesBySprint(Long sprintId);
}
