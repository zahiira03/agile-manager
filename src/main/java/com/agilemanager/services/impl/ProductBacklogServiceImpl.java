package com.agilemanager.services.impl;

import com.agilemanager.Dtos.ProductBacklogDto;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.Project;
import com.agilemanager.exceptions.ResourceNotFoundException;
import com.agilemanager.mappers.ProductBacklogMapper;
import com.agilemanager.repository.ProductBacklogRepository;
import com.agilemanager.repository.ProjectRepository;
import com.agilemanager.services.interfaces.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBacklogServiceImpl implements ProductBacklogService {

    private final ProductBacklogRepository productBacklogRepository;
    private final ProductBacklogMapper productBacklogMapper;
    private final ProjectRepository projectRepository;

    @Override
    public ProductBacklogDto findById(Long id) {
        ProductBacklog productBacklog = productBacklogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "ProductBacklog not found: " + id
        ));
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
    public ProductBacklogDto updateProductBacklog(Long id, ProductBacklogDto productBacklogDto) {

        ProductBacklog existing = productBacklogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog not found: " + id));

        productBacklogMapper.updateEntityFromDto(productBacklogDto, existing);
        ProductBacklog saved = productBacklogRepository.save(existing);
        return productBacklogMapper.toDto(saved);
    }

    @Override
    public void deleteProductBacklog(Long id) {
        ProductBacklog backlog = productBacklogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog not found: " + id));

        Project project = backlog.getProject();
        if (project != null) {
            projectRepository.delete(project);
        } else {

            productBacklogRepository.delete(backlog);
        }
    }

}
