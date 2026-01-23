package com.agilemanager.repository;

import com.agilemanager.entities.Task;
import com.agilemanager.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserStoryId(Long userStoryId);
    List<Task> findByStatus(Status status);
}
