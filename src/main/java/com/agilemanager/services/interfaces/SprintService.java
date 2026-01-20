package com.agilemanager.services.interfaces;

import com.agilemanager.entities.Sprint;
import com.agilemanager.entities.UserStory;

import java.util.List;

public interface SprintService {

    Sprint createSprint(Sprint sprint);

    List<Sprint> getAllSprints();

    Sprint getSprint(Long id);

    List<UserStory> getSprintBacklog(Long sprintId);

    UserStory addUserStoryToSprint(Long sprintId, Long userStoryId);

    UserStory removeUserStoryFromSprint(Long sprintId, Long userStoryId);
}
