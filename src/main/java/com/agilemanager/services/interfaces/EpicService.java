package com.agilemanager.services.interfaces;
import com.agilemanager.entities.Epic;

import java.util.List;

public interface EpicService {

    Epic create(Long productBacklogId,Epic epic);                 // créer un epic
    Epic update(Long id, Epic epic);        // modifier un epic
    Epic findById(Long id);                 // chercher par id
    List<Epic> findAll();                   // lister tous les epics
    List<Epic> findByProductBacklog(Long backlogId); // lister les epics d’un backlog
    void delete(Long id);                   // supprimer un epic
}