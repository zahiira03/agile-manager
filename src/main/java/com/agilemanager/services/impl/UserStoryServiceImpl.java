package com.agilemanager.services.impl;

import com.agilemanager.Dtos.UserStoryDto;
import com.agilemanager.entities.Epic;
import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.exceptions.ResourceNotFoundException;
import com.agilemanager.mappers.UserStoryMapper;
import com.agilemanager.repository.EpicRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor //fait l`injection avec constructeur
public class UserStoryServiceImpl implements UserStoryService {

        private final UserStoryRepository userStoryRepository;
        private final EpicRepository epicRepository;
        private final UserStoryMapper userStoryMapper;

        @Override
        public UserStoryDto createUserStory(Long epicId, UserStoryDto userStoryDto) {

            Epic epic = epicRepository.findById(epicId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Epic not found: " + epicId
                    ));
            UserStory userStory = userStoryMapper.toEntity(userStoryDto);
            userStory.setEpic(epic);
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


    // hadi rat3tini ri les userstory li kaynin fwahed epics

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




    @Override
        public UserStoryDto updateUserStory(Long id, UserStoryDto userStoryDto) {

            UserStory existing = userStoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "UserStory not found: " + id
                    ));

            userStoryMapper.updateEntityFromDto(userStoryDto, existing);
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

