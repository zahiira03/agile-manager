package com.agilemanager.services.impl;

import com.agilemanager.entities.Sprint;
import com.agilemanager.entities.UserStory;
import com.agilemanager.repository.SprintRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.SprintService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final UserStoryRepository userStoryRepository;

    public SprintServiceImpl(SprintRepository sprintRepository,
                             UserStoryRepository userStoryRepository) {
        this.sprintRepository = sprintRepository;
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public Sprint createSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @Override
    public Sprint getSprint(Long id) {
        return sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));
    }

    @Override
    public List<UserStory> getSprintBacklog(Long sprintId) {
        return userStoryRepository.findBySprintId(sprintId);
    }

    @Override
    @Transactional
    public UserStory addUserStoryToSprint(Long sprintId, Long userStoryId) {
        Sprint sprint = getSprint(sprintId);
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("UserStory not found"));

        userStory.setSprint(sprint);
        return userStoryRepository.save(userStory);
    }

    @Override
    @Transactional
    public UserStory removeUserStoryFromSprint(Long sprintId, Long userStoryId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("UserStory not found"));

        userStory.setSprint(null);
        return userStoryRepository.save(userStory);
    }
}
