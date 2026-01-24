package com.agilemanager.services.interfaces;

import com.agilemanager.Dtos.SprintDTO;
import com.agilemanager.Dtos.UserStoryDto;

import java.util.List;

public interface SprintService {


    SprintDTO createSprint(SprintDTO dto);
    List<SprintDTO> getAllSprints();
    SprintDTO getSprint(Long id);

    SprintDTO getSprintById(Long id);

    SprintDTO updateSprint(Long id, SprintDTO sprintDTO);

    void deleteSprint(Long id);
    List<SprintDTO> getSprintsByProject(Long projectId);
    void assignUserStoryToSprint(Long sprintId, Long userStoryId);
    List<UserStoryDto> getUserStoriesBySprint(Long sprintId);
}
