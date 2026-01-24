package com.agilemanager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Entity
@Setter @Getter //genere les getter et setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductBacklog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name est obligatoire")
    @Column(nullable = false)
    private String name;
    private String description;

    @OneToOne(mappedBy = "productBacklog")
    private Project project;

    @OneToMany(mappedBy = "productBacklog", cascade = CascadeType.ALL)
    private List<Epic> epics;


}