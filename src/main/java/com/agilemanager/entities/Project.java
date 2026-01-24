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

    @Column(name = "project_key", nullable = false, unique = true, length = 10)
    private String projectKey;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;




    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true ,optional = false)
    @JoinColumn(name = "product_backlog_id", nullable = false)
    private ProductBacklog productBacklog;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprints = new ArrayList<>();

    // ===== Hooks =====
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
