package com.agilemanager.services.impl;

import com.agilemanager.Dtos.SprintDTO;
import com.agilemanager.Dtos.UserStoryDto;
import com.agilemanager.entities.Project;
import com.agilemanager.entities.Sprint;
import com.agilemanager.entities.UserStory;
import com.agilemanager.mappers.SprintMapper;
import com.agilemanager.mappers.UserStoryMapper;
import com.agilemanager.repository.ProjectRepository;
import com.agilemanager.repository.SprintRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;
    private final UserStoryRepository userStoryRepository;

    private final SprintMapper sprintMapper;


    private final UserStoryMapper userStoryMapper;

    private Sprint getSprintEntity(Long id) {
        return sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found with id: " + id));
    }

    private Project getProjectEntity(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    private UserStory getUserStoryEntity(Long id) {
        return userStoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserStory not found with id: " + id));
    }

    @Override
    public SprintDTO createSprint(SprintDTO dto) {
        if (dto.getProjectId() == null) {
            throw new RuntimeException("projectId is required to create a sprint");
        }

        Project project = getProjectEntity(dto.getProjectId());

        Sprint sprint = sprintMapper.toEntity(dto);
        sprint.setProject(project);

        Sprint saved = sprintRepository.save(sprint);
        return sprintMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintDTO> getAllSprints() {
        return sprintMapper.toDtoList(sprintRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
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


        sprintMapper.updateEntityFromDto(dto, sprint);


        if (dto.getProjectId() != null) {
            Project project = getProjectEntity(dto.getProjectId());
            sprint.setProject(project);
        }

        Sprint saved = sprintRepository.save(sprint);
        return sprintMapper.toDto(saved);
    }

    @Override
    public void deleteSprint(Long id) {
        Sprint sprint = getSprintEntity(id);
        sprintRepository.delete(sprint);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintDTO> getSprintsByProject(Long projectId) {
        return sprintMapper.toDtoList(sprintRepository.findByProjectId(projectId));
    }

    @Override
    public void assignUserStoryToSprint(Long sprintId, Long userStoryId) {
        Sprint sprint = getSprintEntity(sprintId);
        UserStory userStory = getUserStoryEntity(userStoryId);


        userStory.setSprint(sprint);
        userStoryRepository.save(userStory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserStoryDto> getUserStoriesBySprint(Long sprintId) {
        List<UserStory> list = userStoryRepository.findBySprintId(sprintId);

        return list.stream()
                .map(userStoryMapper::toDto)
                .toList();
    }

}