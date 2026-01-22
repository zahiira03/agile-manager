package com.agilemanager.repository;

import com.agilemanager.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    boolean existsByProjectKey(String projectKey);
}
