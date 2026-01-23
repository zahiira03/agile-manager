package com.agilemanager.services.impl;

import com.agilemanager.Dtos.TaskDTO;
import com.agilemanager.entities.Task;
import com.agilemanager.entities.User;
import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.repository.TaskRepository;
import com.agilemanager.repository.UserRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserStoryRepository userStoryRepository;
    private final UserRepository userRepository;

    // -----------------------
    // Helpers (get entity)
    // -----------------------
    private Task getTaskEntity(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    private UserStory getUserStoryEntity(Long id) {
        return userStoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserStory not found with id: " + id));
    }

    private User getUserEntityOrNull(Long id) {
        if (id == null) return null;
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // -----------------------
    // Mapping (Entity <-> DTO)
    // -----------------------
    private TaskDTO toDto(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());

        dto.setUser(task.getAssignedUser() != null ? task.getAssignedUser().getId() : null);
        dto.setUserStoryId(task.getUserStory() != null ? task.getUserStory().getId() : null);

        return dto;
    }

    private Task toEntity(TaskDTO dto, UserStory userStory, User assignedUser) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .userStory(userStory)
                .assignedUser(assignedUser)
                .build();
    }

    // -----------------------
    // CRUD
    // -----------------------
    @Override
    public TaskDTO createTask(TaskDTO dto) {
        // userStoryId optional فـ create (إلا بغيتي تجبرها، دير check هنا)
        UserStory userStory = (dto.getUserStoryId() != null) ? getUserStoryEntity(dto.getUserStoryId()) : null;
        User assignedUser = getUserEntityOrNull(dto.getUser());

        Task task = toEntity(dto, userStory, assignedUser);
        Task saved = taskRepository.save(task);
        return toDto(saved);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public TaskDTO getTask(Long id) {
        return toDto(getTaskEntity(id));
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = getTaskEntity(id);

        // update fields
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());

        // update relations
        UserStory userStory = (dto.getUserStoryId() != null) ? getUserStoryEntity(dto.getUserStoryId()) : null;
        User assignedUser = getUserEntityOrNull(dto.getUser());

        task.setUserStory(userStory);
        task.setAssignedUser(assignedUser);

        Task updated = taskRepository.save(task);
        return toDto(updated);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskEntity(id);
        taskRepository.delete(task);
    }

    // -----------------------
    // Filters
    // -----------------------
    @Override
    public List<TaskDTO> getTasksByStatus(Status status) {

        return taskRepository.findAll().stream()
                .filter(t -> t.getStatus() == status)
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<TaskDTO> getTasksByUserStory(Long userStoryId) {
        return taskRepository.findByUserStoryId(userStoryId).stream()
                .map(this::toDto)
                .toList();
    }

    // -----------------------
    // Assign / Unassign UserStory
    // -----------------------
    @Override
    public TaskDTO assignTaskToUserStory(Long taskId, Long userStoryId) {
        Task task = getTaskEntity(taskId);
        UserStory userStory = getUserStoryEntity(userStoryId);

        task.setUserStory(userStory);
        Task saved = taskRepository.save(task);

        return toDto(saved);
    }

    @Override
    public void unassignTaskFromUserStory(Long taskId) {
        Task task = getTaskEntity(taskId);
        task.setUserStory(null);
        taskRepository.save(task);
    }
}
