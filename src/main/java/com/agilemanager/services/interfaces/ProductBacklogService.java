package com.agilemanager.services.interfaces;

import com.agilemanager.Dtos.ProductBacklogDto;
import com.agilemanager.entities.ProductBacklog;

import java.util.List;

public interface ProductBacklogService {

    List< ProductBacklogDto> findAll();
    ProductBacklogDto findById(Long id);
    ProductBacklogDto createProductBacklog(ProductBacklogDto  productBacklogDto);
    ProductBacklogDto updateProductBacklog(Long id,  ProductBacklogDto  productBacklogDto);
    void deleteProductBacklog(Long id);
}