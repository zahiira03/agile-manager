package com.agilemanager.entities;

import com.agilemanager.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MoSCoW priority;   // MoSCoW

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // ===== Relations ManyToOne (on ignore en JSON pour éviter les boucles) =====

    @ManyToOne
    @JoinColumn(name="epic_id", nullable = true)
    @JsonIgnore
    private Epic epic;

    @ManyToOne
    @JoinColumn(name="productBacklog_id", nullable = false)
    @JsonIgnore
    private ProductBacklog productBacklog;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    @JsonIgnore
    private Sprint sprint;

    // ===== OneToMany =====

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
}
