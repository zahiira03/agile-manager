package com.agilemanager.controllers;

import com.agilemanager.entities.Epic;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.services.interfaces.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService epicService;

    // 1️⃣ Lister tous les epics

    @GetMapping
    public List<Epic> getAllEpics() {
        return epicService.findAll();
    }


    // 2️⃣ Récupérer un epic par id

    @GetMapping("/{id}")
    public Epic getEpicById(@PathVariable Long id) {
        return epicService.findById(id);
    }


    // 3️⃣ Créer un epic dans un product backlog

    @PostMapping("/productBacklog/{productbacklogId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Epic createEpic(
            @PathVariable Long productbacklogId,
            @RequestBody Epic epic
    ) {
        return epicService.create(productbacklogId,epic);
    }


    // 4️⃣ Mettre à jour un epic

    @PutMapping("/{id}")
    public Epic updateEpic(
            @PathVariable Long id,
            @RequestBody Epic epic
    ) {
        return epicService.update(id, epic);
    }


    // 5️⃣ Supprimer un epic

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEpic(@PathVariable Long id) {
        epicService.delete(id);
    }

    // 5️⃣  epics associe a un productbacklog
    @GetMapping("/productBacklog/{productbacklogId}")
    public List<Epic> findByProductBacklog(@PathVariable long productbacklogId){
        return epicService.findByProductBacklog(productbacklogId);
    }
}
