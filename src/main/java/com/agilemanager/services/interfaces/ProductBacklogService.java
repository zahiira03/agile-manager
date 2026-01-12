package com.agilemanager.services.interfaces;

import com.agilemanager.entities.ProductBacklog;

import java.util.List;

public interface ProductBacklogService {

    ProductBacklog create(ProductBacklog backlog);
    List<ProductBacklog> findAll();
    ProductBacklog findById(Long id);
    ProductBacklog update(Long id, ProductBacklog backlog);
    void delete(Long id);
}