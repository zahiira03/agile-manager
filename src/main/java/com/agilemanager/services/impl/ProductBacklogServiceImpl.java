package com.agilemanager.services.impl;

import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.repository.ProductBacklogRepository;
import com.agilemanager.services.interfaces.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBacklogServiceImpl implements ProductBacklogService {

    private final ProductBacklogRepository productBacklogRepository;

    @Override
    public ProductBacklog create(ProductBacklog productBacklog) {
        if  (productBacklog == null) {
            throw new IllegalArgumentException("productBacklog cannot be null");
        }
        return productBacklogRepository.save(productBacklog);
    }

    @Override
    public List<ProductBacklog> findAll() {
        return productBacklogRepository.findAll();
    }

    @Override
    public ProductBacklog findById(Long id) {
        return productBacklogRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("ProductBacklog not found with id " + id)
                );

    }

    @Override
    public ProductBacklog update(Long id, ProductBacklog productBacklog) {

        ProductBacklog existing = productBacklogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductBacklog not found: " + id));

        // On conserve l’ID existant
        productBacklog.setId(existing.getId());

        return productBacklogRepository.save(productBacklog);
    }

    @Override
    public void delete(Long id) {
        productBacklogRepository.deleteById(id);
    }
}
