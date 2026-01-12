package com.agilemanager.repository;

import com.agilemanager.entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agilemanager.entities.enums.*;
import java.util.List;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

    List<UserStory> findByProductBacklogId(Long productBacklogId);
    List<UserStory> findByEpicId(Long epicId);
    List<UserStory> findByPriority(MoSCoW priority);
    List<UserStory> findByStatus(Status status);

}
