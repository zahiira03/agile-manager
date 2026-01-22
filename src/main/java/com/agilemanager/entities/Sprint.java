package com.agilemanager.entities;

import com.agilemanager.entities.enums.SprintStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private SprintStatus status;
    // Sprint Backlog = liste des user stories affectées à ce sprint
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserStory> userStories = new ArrayList<>();



    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
