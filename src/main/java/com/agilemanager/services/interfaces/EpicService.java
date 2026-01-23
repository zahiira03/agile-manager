package com.agilemanager.services.interfaces;
import com.agilemanager.Dtos.EpicDto;
import com.agilemanager.entities.Epic;

import java.util.List;

public interface EpicService {

    EpicDto  createEpic(Long productBacklogId, EpicDto epicdto);                 // créer un epic
    EpicDto  updateEpic(Long epicId, EpicDto epicdto);        // modifier un epic
    EpicDto  findById(Long id);                 // chercher par id
    List<EpicDto> findAll();                   // lister tous les epics
    List<EpicDto> findByProductBacklog(Long productbacklogId); // lister les epics d’un backlog
    void deleteEpic(Long id);                   // supprimer un epic
}