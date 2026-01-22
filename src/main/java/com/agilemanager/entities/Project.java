package com.agilemanager.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // clé courte du projet (ex: AGILE, SCRUM01…)
    @Column(nullable = false, unique = true, length = 10)
    private String key;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ===== Relations =====

    // Project 1 ---- 1 ProductBacklog
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "product_backlog_id", nullable = false)
    private ProductBacklog productBacklog;

    // Project 1 ---- * Sprint
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprints = new ArrayList<>();

    // ===== Hooks =====
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
