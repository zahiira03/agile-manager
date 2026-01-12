package com.agilemanager.controllers;

import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.services.interfaces.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlogs")
@RequiredArgsConstructor
public class ProductBacklogController {

    private final ProductBacklogService productBacklogService;

    @GetMapping("/hello")
    public String hello() {
        return "Ça marche !";
    }

    @PostMapping
    public ProductBacklog create(@RequestBody ProductBacklog productBacklog) {

        return productBacklogService.create(productBacklog);
    }

    @PutMapping("/{id}")
    public ProductBacklog update(@PathVariable Long id,
                                 @RequestBody ProductBacklog productBacklog) {
        return productBacklogService.update(id, productBacklog);
    }

    @GetMapping
    public List<ProductBacklog> getAll() {
        return productBacklogService.findAll();
    }

    @GetMapping("/{id}")
    public ProductBacklog getById(@PathVariable Long id) {
        return productBacklogService.findById(id);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productBacklogService.delete(id);
    }
}
