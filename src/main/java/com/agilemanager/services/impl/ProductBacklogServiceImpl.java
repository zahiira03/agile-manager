package com.agilemanager.services.impl;

import com.agilemanager.Dtos.ProductBacklogDto;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.Project;
import com.agilemanager.exceptions.ResourceNotFoundException;
import com.agilemanager.mappers.ProductBacklogMapper;
import com.agilemanager.repository.ProductBacklogRepository;
import com.agilemanager.services.interfaces.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBacklogServiceImpl implements ProductBacklogService {

    private final ProductBacklogRepository productBacklogRepository;
    private final ProductBacklogMapper productBacklogMapper;

    @Override
    public ProductBacklogDto findById(Long id) {
        ProductBacklog productBacklog = productBacklogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "ProductBacklog not found: " + id
        ));

//                orElseThrow(() ->
//                new RuntimeException("ProductBacklog not found with id " + id)
//        );
        return productBacklogMapper.toDto(productBacklog);
    }

    @Override
    public List< ProductBacklogDto> findAll() {

        return productBacklogRepository.findAll()
                .stream()
                .map(productBacklogMapper::toDto)
                .toList();
    }


    @Override
    public  ProductBacklogDto createProductBacklog(ProductBacklogDto productBacklogDto) {
        if  (productBacklogDto == null) {
            throw new IllegalArgumentException("productBacklog cannot be null");
        }
        ProductBacklog productbacklog = productBacklogMapper.toEntity(productBacklogDto);
        ProductBacklog savedProductBacklog= productBacklogRepository.save(productbacklog);

        return productBacklogMapper.toDto(savedProductBacklog);
    }


    @Override
    public ProductBacklogDto updateProductBacklog(Long id, ProductBacklogDto productBacklogDto) {

        ProductBacklog existing = productBacklogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductBacklog not found: " + id));

        productBacklogMapper.updateEntityFromDto(productBacklogDto, existing);

        ProductBacklog saved = productBacklogRepository.save(existing);


        return productBacklogMapper.toDto(saved);
    }

    @Override
    public void deleteProductBacklog(Long id) {
        ProductBacklog productBacklog = productBacklogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog not found: " + id));
        productBacklogRepository.delete(productBacklog);
    }
}
