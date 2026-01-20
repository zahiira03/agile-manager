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

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Sprint sprint;

    public Sprint getSprint() { return sprint; }
    public void setSprint(Sprint sprint) { this.sprint = sprint; }



    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private java.util.List<Task> tasks = new java.util.ArrayList<>();

    public java.util.List<Task> getTasks() { return tasks; }
    public void setTasks(java.util.List<Task> tasks) { this.tasks = tasks; }


}
