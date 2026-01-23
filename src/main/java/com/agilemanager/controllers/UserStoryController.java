package com.agilemanager.controllers;


import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.services.interfaces.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/userStories")
@RequiredArgsConstructor

public class UserStoryController {

    private final UserStoryService userStoryService;

    @PostMapping("/productBacklog/{productbacklogId}")
    public UserStory createInProductBacklog(@PathVariable long productbacklogId,@RequestBody UserStory userStory){

        return userStoryService.createInProductBacklog(productbacklogId,userStory);
    }

    @PostMapping("/productBacklog/{productbacklogId}/epic/{epicId}")
    public UserStory createInEpic(@PathVariable Long productbacklogId,
                                  @PathVariable Long epicId,
                                  @RequestBody UserStory userStory){


        return userStoryService.createInEpic(productbacklogId,epicId,userStory);
    }
    @GetMapping
    public List<UserStory> getAll(){
        return userStoryService.findAll();
    }
    @GetMapping("/{id}")
    public UserStory getById(@PathVariable Long id){
        return userStoryService.findById(id);
    }

    @GetMapping("/productbacklog/{productbacklogId}")
    public List<UserStory> getByProductBacklog(@PathVariable Long productbacklogId){
        return userStoryService.findByProductBacklog(productbacklogId);
    }

    @GetMapping("/epic/{epicId}")
    public List<UserStory> getByEpic(@PathVariable  Long epicId){
        return userStoryService.findByEpic(epicId);
    }

    @GetMapping("/priority/{priority}")
    public List<UserStory> getByPriority(@PathVariable MoSCoW priority){
        return userStoryService.findByPriority(priority) ;
    }

    @GetMapping("/status/{status}")
    public List<UserStory> getByStatus(@PathVariable Status status){
        return userStoryService.findByStatus(status);
    }

    @PutMapping("/{id}")
    public UserStory update(@PathVariable Long id, @RequestBody UserStory userStory){
        return userStoryService.update(id, userStory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userStoryService.delete(id);
    }








}
