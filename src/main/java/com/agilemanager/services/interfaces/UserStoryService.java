package com.agilemanager.services.interfaces;

import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;

import java.util.List;

public interface UserStoryService {

    UserStory createInEpic(Long productbacklogId, Long epicId, UserStory userStory);
    List<UserStory> findAll();
    UserStory findById(Long id);
    List<UserStory> findByProductBacklog(Long productbacklogId);
    List<UserStory> findByEpic(Long epicId);
    List<UserStory> findByPriority(MoSCoW priority);
    List<UserStory> findByStatus(Status status);
    UserStory update(Long id, UserStory userStory);
    void delete(Long id);

}
