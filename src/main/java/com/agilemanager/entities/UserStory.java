package com.agilemanager.entities;

import com.agilemanager.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
}
