package com.agilemanager.repository;

import com.agilemanager.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {}
