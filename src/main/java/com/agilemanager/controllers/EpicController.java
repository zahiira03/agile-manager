package com.agilemanager.controllers;

import com.agilemanager.Dtos.EpicDto;
import com.agilemanager.services.interfaces.EpicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class EpicController {

    private final EpicService epicService;


    @PostMapping("/product-backlogs/{productBacklogId}/epics")
    public ResponseEntity<EpicDto> createEpic(
            @PathVariable Long productBacklogId,
            @RequestBody EpicDto epicDto
    ) {
        EpicDto created = epicService.createEpic(productBacklogId, epicDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    // GET epics by ProductBacklog
    @GetMapping("/product-backlogs/{productBacklogId}/epics")
    public ResponseEntity<List<EpicDto>> getEpicsByBacklog(@PathVariable Long productBacklogId) {
        return ResponseEntity.ok(epicService.findByProductBacklog(productBacklogId));
    }

    // GET all epics dyal ga3 les product backlog
    // GET /api/epics
    @GetMapping("/epics")
    public ResponseEntity<List<EpicDto>> getAllEpics() {
        return ResponseEntity.ok(epicService.findAll());
    }

    // GET epic by id
    @GetMapping("/epics/{id}")
    public ResponseEntity<EpicDto> getEpicById(@PathVariable Long id) {
        return ResponseEntity.ok(epicService.findById(id));
    }


    @PutMapping("/epics/{id}")
    public ResponseEntity<EpicDto> updateEpic(
            @PathVariable Long id,
            @RequestBody EpicDto epicDto
    ) {
        return ResponseEntity.ok(epicService.updateEpic(id, epicDto));
    }

    @DeleteMapping("/epics/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Long id) {
        epicService.deleteEpic(id);
        return ResponseEntity.noContent().build();
    }
}
