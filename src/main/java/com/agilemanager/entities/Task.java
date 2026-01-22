package com.agilemanager.entities;

import com.agilemanager.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    @JsonBackReference
    private UserStory userStory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User assignedUser;


}
