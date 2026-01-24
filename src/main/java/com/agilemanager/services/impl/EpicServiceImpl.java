package com.agilemanager.services.impl;

import com.agilemanager.Dtos.EpicDto;
import com.agilemanager.entities.Epic;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.UserStory;
import com.agilemanager.exceptions.ResourceNotFoundException;
import com.agilemanager.mappers.EpicMapper;
import com.agilemanager.repository.EpicRepository;
import com.agilemanager.repository.ProductBacklogRepository;
import com.agilemanager.services.interfaces.EpicService;
import com.agilemanager.services.interfaces.ProductBacklogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;
    private final ProductBacklogRepository productBacklogRepository;
    private final EpicMapper epicMapper;


    @Override
    public EpicDto createEpic(Long productBacklogId, EpicDto epicDto) {

        if (epicDto.getTitle() == null || epicDto.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Epic title is required");
        }

        // règle: un epic doit avoir un backlog
        if (productBacklogId == null ) {
            throw new RuntimeException("Epic must be linked to a ProductBacklog");
        }

        ProductBacklog productbacklog = productBacklogRepository.findById(productBacklogId).orElseThrow(() -> new ResourceNotFoundException(
                "ProductBacklog not found: " + productBacklogId
        ));
        Epic epic = epicMapper.toEntity(epicDto);
        epic.setProductBacklog(productbacklog);
        Epic savedepic = epicRepository.save(epic);
        return epicMapper.toEpicDto(savedepic);
    }
    @Override
    public EpicDto updateEpic(Long id, EpicDto epicDto) {

        Epic existing = epicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Epic not found: " + id));; // finfByid va genere exception si pas trouver
        epicMapper.updateEntityFromDto(epicDto, existing);
        Epic savedEpic = epicRepository.save(existing);
        return epicMapper.toEpicDto(savedEpic);


    }

    @Override
    public void deleteEpic(Long id){
        Epic existing  = epicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "epic not found: " + id
        ));

        epicRepository.deleteById(id);
    }

    @Override
    public EpicDto findById(Long id) {
        Epic epic = epicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Epic not found with id " + id));
        return epicMapper.toEpicDto(epic);

    }
    @Override
    public List<EpicDto> findAll(){// lister tous les epics
        return epicRepository.findAll()
                .stream()
                .map(epicMapper::toEpicDto)
                .toList();
    }

    @Override
    public List<EpicDto> findByProductBacklog(Long productBacklogId) {
        if (!productBacklogRepository.existsById(productBacklogId)) {
            throw new ResourceNotFoundException("ProductBacklog not found: " + productBacklogId);
        }

        return epicRepository.findByProductBacklogId(productBacklogId)
                .stream()
                .map(epicMapper::toEpicDto)
                .toList();
    }
    }

