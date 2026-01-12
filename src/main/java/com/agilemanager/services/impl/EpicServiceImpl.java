package com.agilemanager.services.impl;

import com.agilemanager.entities.Epic;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.UserStory;
import com.agilemanager.repository.EpicRepository;
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
    //private final ProductBacklogRepository productBacklogRepository;
    private final ProductBacklogService productBacklogService;

    @Override
    public Epic create(Long productBacklogId, Epic epic) {

        if (epic.getTitle() == null || epic.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Epic title is required");
        }

        // règle: un epic doit avoir un backlog
        if (productBacklogId == null ) {
            throw new RuntimeException("Epic must be linked to a ProductBacklog");
        }

        // ✅ vérifie existence via le service (SRP + DIP)
        ProductBacklog productbacklog = productBacklogService.findById(productBacklogId);

        epic.setProductBacklog(productbacklog);
        return epicRepository.save(epic);
    }
    @Override
    public Epic update(Long id, Epic epic) {
        //trouver epic a modifier
        Epic existing = findById(id); // finfByid va genere exception si pas trouver
        if(epic.getTitle()==null  || epic.getTitle().trim().isEmpty())
        {
                throw new RuntimeException("Epic title cannot be null or empty");
        }

            //je dois aussi verifie si title n`est pas vide car il est nullable = false
            existing.setTitle(epic.getTitle());
            existing.setDescription(epic.getDescription());
            return epicRepository.save(existing);


    }

    @Override
    @Transactional
    public void delete(Long id){
        Epic epic = findById(id);// exception si non trouvé

        // 1️Détacher les UserStories
        if(epic.getUserStories()!= null){
            for(UserStory userStory : epic.getUserStories()){
                userStory.setEpic(null);
        }
        }

        epicRepository.delete(epic);
        //epicRepository.deleteById(id);
    }

    @Override
    public Epic findById(Long id) {
        return epicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Epic not found with id " + id));
    }
    @Override
    public List<Epic> findAll(){// lister tous les epics
        return epicRepository.findAll();
    }

    @Override
    public List<Epic> findByProductBacklog(Long productBacklogId){

        productBacklogService.findById(productBacklogId);// exception si absent

        return epicRepository.findByProductBacklogId(productBacklogId);
    } // lister les epics d’un backlog

}
