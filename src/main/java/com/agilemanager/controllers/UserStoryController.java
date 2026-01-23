package com.agilemanager.controllers;

import com.agilemanager.Dtos.UserStoryDto;
import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.services.interfaces.UserStoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserStoryController {

    private final UserStoryService userStoryService;

    @PostMapping("/epics/{epicId}/user-stories")
    public ResponseEntity<UserStoryDto> createUserStory(
            @PathVariable Long epicId,
            @RequestBody UserStoryDto userStoryDto
    ) {
        UserStoryDto created = userStoryService.createUserStory(epicId, userStoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/user-stories/{id}")
    public ResponseEntity<UserStoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userStoryService.findById(id));
    }

    @GetMapping("/epics/{epicId}/user-stories")
    public ResponseEntity<List<UserStoryDto>> getByEpic(@PathVariable Long epicId) {
        return ResponseEntity.ok(userStoryService.findByEpicId(epicId));
    }

    @PutMapping("/user-stories/{id}")
    public ResponseEntity<UserStoryDto> update(
            @PathVariable Long id,
            @RequestBody UserStoryDto userStoryDto
    ) {
        return ResponseEntity.ok(userStoryService.updateUserStory(id, userStoryDto));
    }


    @DeleteMapping("/user-stories/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userStoryService.deleteUserStory(id);
        return ResponseEntity.noContent().build();
    }


//    // =========================================
//    // GET all UserStories
//    // GET /api/user-stories
//    // + optional filters: ?priority=... &status=...
//    // =========================================
//    @GetMapping("/user-stories")
//    public ResponseEntity<List<UserStoryDto>> getAll(
//            @RequestParam(required = false) MoSCoW priority,
//            @RequestParam(required = false) Status status
//    ) {
//
//        // Si les deux filtres existent, on applique un choix simple :
//        // priorité > status (ou tu peux créer une méthode repo combinée plus tard).
//        if (priority != null) {
//            return ResponseEntity.ok(userStoryService.findByPriority(priority));
//        }
//        if (status != null) {
//            return ResponseEntity.ok(userStoryService.findByStatus(status));
//        }
//
//        return ResponseEntity.ok(userStoryService.findAll());
//    }



}
