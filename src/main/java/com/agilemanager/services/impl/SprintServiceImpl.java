package com.agilemanager.services.impl;

import com.agilemanager.Dtos.SprintDTO;
import com.agilemanager.entities.Project;
import com.agilemanager.entities.Sprint;
import com.agilemanager.entities.UserStory;
import com.agilemanager.mappers.SprintMapper;
import com.agilemanager.mappers.UserStoryMapper;
import com.agilemanager.repository.ProjectRepository;
import com.agilemanager.repository.SprintRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.SprintService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;
    private final UserStoryRepository userStoryRepository;

    private final SprintMapper sprintMapper;
    private final UserStoryMapper userStoryMapper; // عندك mapper ديال UserStory

    private Sprint getSprintEntity(Long id) {
        return sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found with id: " + id));
    }

    private Project getProjectEntity(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    @Override
    public SprintDTO createSprint(SprintDTO dto) {
        // 1) نجيبو Project (Mandatory)
        if (dto.getProjectId() == null) {
            throw new RuntimeException("projectId is required to create a sprint");
        }
        Project project = getProjectEntity(dto.getProjectId());

        // 2) DTO -> Entity
        Sprint sprint = sprintMapper.toEntity(dto, project);

        // 3) Save
        Sprint saved = sprintRepository.save(sprint);

        // 4) Entity -> DTO
        return sprintMapper.toDto(saved);
    }

    @Override
    public List<SprintDTO> getAllSprints() {
        return sprintRepository.findAll()
                .stream()
                .map(sprintMapper::toDto)
                .toList();
    }

    @Override
    public SprintDTO getSprint(Long id) {
        return sprintMapper.toDto(getSprintEntity(id));
    }

    @Override
    public SprintDTO getSprintById(Long id) {
        return null;
    }

    @Override
    public SprintDTO updateSprint(Long id, SprintDTO dto) {
        Sprint sprint = getSprintEntity(id);

        if (dto.getProjectId() == null) {
            throw new RuntimeException("projectId is required to update a sprint");
        }
        Project project = getProjectEntity(dto.getProjectId());

        sprintMapper.updateEntity(sprint, dto, project);

        Sprint updated = sprintRepository.save(sprint);
        return sprintMapper.toDto(updated);
    }

    @Override
    public void deleteSprint(Long id) {
        Sprint sprint = getSprintEntity(id);
        sprintRepository.delete(sprint);
    }

    // -------------------------
    // Sprint Backlog (UserStory)
    // -------------------------

    @Override
    public List<UserStoryDTO> getSprintBacklog(Long sprintId) {

        return userStoryRepository.findBySprintId(sprintId)
                .stream()
                .map(userStoryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UserStoryDTO addUserStoryToSprint(Long sprintId, Long userStoryId) {
        Sprint sprint = getSprintEntity(sprintId);

        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("UserStory not found with id: " + userStoryId));

        userStory.setSprint(sprint);
        UserStory saved = userStoryRepository.save(userStory);

        return userStoryMapper.toDto(saved);
    }

    @Override
    @Transactional
    public UserStoryDTO removeUserStoryFromSprint(Long sprintId, Long userStoryId) {
        // كنحيّدو sprint من user story
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("UserStory not found with id: " + userStoryId));

        userStory.setSprint(null);
        UserStory saved = userStoryRepository.save(userStory);

        return userStoryMapper.toDto(saved);
    }
}
