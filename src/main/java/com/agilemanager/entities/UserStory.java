package com.agilemanager.entities;

import com.agilemanager.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @ManyToOne
    @JoinColumn(name="epic_id",nullable = true)
    @JsonBackReference
    private Epic epic;

    @ManyToOne
    @JoinColumn(name="productBacklog_id",nullable = false)
    @JsonBackReference
    private ProductBacklog productBacklog;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    @JsonBackReference
    private Sprint sprint;


    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();



}
