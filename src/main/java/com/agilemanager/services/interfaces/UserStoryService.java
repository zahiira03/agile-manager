package com.agilemanager.services.interfaces;

import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.Dtos.UserStoryDto;
import java.util.List;

public interface UserStoryService {

    UserStoryDto createUserStory(Long epicId, UserStoryDto userStoryDto);
    UserStoryDto findById(Long id);
    List<UserStoryDto> findByEpicId(Long epicId);
    List<UserStoryDto> findByPriorityAndStatus(MoSCoW priority, Status status);
    UserStoryDto updateUserStory(Long id, UserStoryDto userStoryDto);
    void deleteUserStory(Long id);

}
