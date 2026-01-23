package com.agilemanager.controllers;

import com.agilemanager.Dtos.ProductBacklogDto;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.services.interfaces.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlogs")
@RequiredArgsConstructor
public class ProductBacklogController {

    private final ProductBacklogService productBacklogService;


//    @PostMapping
//    public ProductBacklogDto create(@RequestBody ProductBacklogDto productBacklogDto) {
//        ProductBacklogDto created = productBacklogService.createProductBacklog(productBacklogDto);
//        return created;
//    }

    @PostMapping
    public ResponseEntity<ProductBacklogDto> create(@RequestBody ProductBacklogDto dto) {
        ProductBacklogDto created = productBacklogService.createProductBacklog(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBacklogDto> update(@PathVariable Long id,
                                 @RequestBody ProductBacklogDto productBacklogdto) {
        return ResponseEntity.status(HttpStatus.OK).body(productBacklogService.updateProductBacklog(id, productBacklogdto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklogDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productBacklogService.findById(id));
    }


    @GetMapping
    public ResponseEntity<List<ProductBacklogDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productBacklogService.findAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productBacklogService.deleteProductBacklog(id);
        return ResponseEntity.noContent().build(); //pas compris
    }
}
