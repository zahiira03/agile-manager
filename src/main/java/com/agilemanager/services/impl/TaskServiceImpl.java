package com.agilemanager.services.impl;

import com.agilemanager.Dtos.TaskDTO;
import com.agilemanager.entities.Task;
import com.agilemanager.entities.User;
import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.mappers.TaskMapper;
import com.agilemanager.repository.TaskRepository;
import com.agilemanager.repository.UserRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserStoryRepository userStoryRepository;
    private final UserRepository userRepository;

    private final TaskMapper taskMapper;

    private Task getTaskEntity(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    private UserStory getUserStoryEntity(Long id) {
        return userStoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserStory not found with id: " + id));
    }

    private User getUserEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        if (dto.getUserStoryId() == null) {
            throw new RuntimeException("userStoryId is required to create a task");
        }

        UserStory us = getUserStoryEntity(dto.getUserStoryId());

        Task task = taskMapper.toEntity(dto);
        task.setUserStory(us);

        if (dto.getAssignedUserId() != null) {
            User user = getUserEntity(dto.getAssignedUserId());
            task.setAssignedUser(user);
        }

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO getTask(Long id) {
        return taskMapper.toDto(getTaskEntity(id));
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = getTaskEntity(id);

        taskMapper.updateEntityFromDto(dto, task);

        if (dto.getUserStoryId() != null) {
            UserStory us = getUserStoryEntity(dto.getUserStoryId());
            task.setUserStory(us);
        }

        if (dto.getAssignedUserId() != null) {
            User user = getUserEntity(dto.getAssignedUserId());
            task.setAssignedUser(user);
        }

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(getTaskEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByStatus(Status status) {
        return taskMapper.toDtoList(taskRepository.findByStatus(status));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByUserStory(Long userStoryId) {
        return taskMapper.toDtoList(taskRepository.findByUserStoryId(userStoryId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByAssignedUser(Long userId) {
        return taskMapper.toDtoList(taskRepository.findByAssignedUserId(userId));
    }

    @Override
    public void assignTaskToUserStory(Long taskId, Long userStoryId) {
        Task task = getTaskEntity(taskId);
        UserStory us = getUserStoryEntity(userStoryId);
        task.setUserStory(us);
        taskRepository.save(task);
    }

    @Override
    public void assignTaskToUser(Long taskId, Long userId) {
        Task task = getTaskEntity(taskId);
        User user = getUserEntity(userId);
        task.setAssignedUser(user);
        taskRepository.save(task);
    }
}
