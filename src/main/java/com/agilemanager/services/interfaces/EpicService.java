package com.agilemanager.services.interfaces;
import com.agilemanager.Dtos.EpicDto;
import com.agilemanager.entities.Epic;

import java.util.List;

public interface EpicService {

    EpicDto  createEpic(Long productBacklogId, EpicDto epicdto);
    EpicDto  updateEpic(Long epicId, EpicDto epicdto);
    EpicDto  findById(Long id);
    List<EpicDto> findAll();
    List<EpicDto> findByProductBacklog(Long productbacklogId);
    void deleteEpic(Long id);
}