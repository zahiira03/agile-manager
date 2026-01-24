package com.agilemanager.services.impl;

import com.agilemanager.Dtos.UserStoryDto;
import com.agilemanager.entities.Epic;
import com.agilemanager.entities.Sprint;
import com.agilemanager.entities.Task;
import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.exceptions.ResourceNotFoundException;
import com.agilemanager.mappers.UserStoryMapper;
import com.agilemanager.repository.*;
import com.agilemanager.services.interfaces.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {

        private final UserStoryRepository userStoryRepository;
        private final EpicRepository epicRepository;
        private final UserStoryMapper userStoryMapper;
        private final SprintRepository sprintRepository;
        private final TaskRepository taskRepository;

        @Override
        public UserStoryDto createUserStory(Long epicId, UserStoryDto userStoryDto) {

            Epic epic = epicRepository.findById(epicId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Epic not found: " + epicId
                    ));
            UserStory userStory = userStoryMapper.toEntity(userStoryDto);
            userStory.setEpic(epic);
            if (userStoryDto.getSprintId() != null) {
                Sprint sprint = sprintRepository.findById(userStoryDto.getSprintId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Sprint not found: " + userStoryDto.getSprintId()
                        ));
                userStory.setSprint(sprint);
            }

            UserStory saved = userStoryRepository.save(userStory);
            return userStoryMapper.toDto(saved);
        }


        @Override
        public UserStoryDto findById(Long id) {
            UserStory userStory = userStoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "UserStory not found: " + id
                    ));
            return userStoryMapper.toDto(userStory);
        }


    @Override
    public List<UserStoryDto> findByPriorityAndStatus(MoSCoW priority, Status status) {

        List<UserStory> userStories;

        if (priority != null && status != null) {
            userStories = userStoryRepository.findByPriorityAndStatus(priority, status);
        }
        else if (priority != null) {
            userStories = userStoryRepository.findByPriority(priority);
        }
        else if (status != null) {
            userStories = userStoryRepository.findByStatus(status);
        }
        else {
            userStories = userStoryRepository.findAll();
        }

        return userStories.stream()
                .map(userStoryMapper::toDto)
                .toList();
    }




        @Override
        public List<UserStoryDto> findByEpicId(Long epicId) {
            Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new ResourceNotFoundException(
                                               "Epic not found: " + epicId
                    ));

            return userStoryRepository.findByEpicId(epicId)
                    .stream()
                    .map(userStoryMapper::toDto)
                    .toList();
        }


    // - sprintId != null : affecter / déplacer dans ce sprint
    // - sprintId == null : retirer du sprint (si tu veux autoriser)
    // Mettre à jour Tasks (relation) uniquement si le client fournit tasksIds
    // - tasksIds == null : ne pas toucher aux tasks
    // - tasksIds == []   : vider la liste
    // - tasksIds == [..] : remplacer par cette liste exacte

    @Override
    public UserStoryDto updateUserStory(Long id, UserStoryDto dto) {

        UserStory existing = userStoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserStory not found: " + id));

        userStoryMapper.updateEntityFromDto(dto, existing);

        if (dto.getSprintId() != null) {
            Sprint sprint = sprintRepository.findById(dto.getSprintId())
                    .orElseThrow(() -> new ResourceNotFoundException("Sprint not found: " + dto.getSprintId()));
            existing.setSprint(sprint);
        } else {

            existing.setSprint(null);
        }

        if (dto.getTasksIds() != null) {

            for (Task t : existing.getTasks()) {
                t.setUserStory(null);
            }


            existing.getTasks().clear();

            if (!dto.getTasksIds().isEmpty()) {
                List<Task> tasks = taskRepository.findAllById(dto.getTasksIds());

                if (tasks.size() != dto.getTasksIds().size()) {
                    throw new ResourceNotFoundException("Certain tasksIds do not exist");
                }


                for (Task t : tasks) {
                    t.setUserStory(existing);
                }

                existing.getTasks().addAll(tasks);
            }
        }

        UserStory saved = userStoryRepository.save(existing);
        return userStoryMapper.toDto(saved);
    }



    @Override
        public void deleteUserStory(Long id) {
            UserStory existing = userStoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "UserStory not found: " + id
                    ));
            userStoryRepository.delete(existing);
        }
    }

