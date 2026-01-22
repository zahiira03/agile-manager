package com.agilemanager.services.impl;

import com.agilemanager.entities.Epic;
import com.agilemanager.entities.UserStory;
import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.entities.enums.MoSCoW;
import com.agilemanager.entities.enums.Status;
import com.agilemanager.repository.ProductBacklogRepository;
import com.agilemanager.repository.UserStoryRepository;
import com.agilemanager.services.interfaces.EpicService;
import com.agilemanager.services.interfaces.ProductBacklogService;
import com.agilemanager.services.interfaces.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor //fait l`injection avec constructeur
public class UserStoryServiceImpl implements UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final ProductBacklogRepository productBacklogRepository;
    private final EpicService epicService;




    private void validateUserStory(UserStory userStory){
        if (userStory == null) {
            throw new RuntimeException("UserStory body is required");
        }


        if(userStory.getTitle() == null || userStory.getTitle().isEmpty()){
            throw new RuntimeException("title is required");
        }
        if (userStory.getStatus() == null) {
            //affectation in progress
            throw new RuntimeException("status is required");
        }
        if (userStory.getPriority() == null) {
            throw new RuntimeException("priority is required");
        }

    }

    private void attachEpicIfProvided(UserStory userStory, Long productBacklogId){

        if(userStory.getEpic() != null && userStory.getEpic().getId() != null){
            Epic existingEpic=epicService.findById(userStory.getEpic().getId());
            long epicBacklogId =existingEpic.getProductBacklog().getId();// je veux recupere id de productbacklog de ce epic
            if (! Objects.equals(epicBacklogId,productBacklogId)){
                throw new RuntimeException("Epic does not belong to the given ProductBacklog");
            }
            userStory.setEpic(existingEpic);
        } else {
            userStory.setEpic(null); // optionnel : clarifie
        }

    }

    @Override
    public  UserStory createInEpic(Long productBacklogId, Long epicId, UserStory userStory){
        // TODO: à implémenter

        //verifie que productBacklog exist
        ProductBacklog productBacklog= productBacklogRepository.findById(productBacklogId).orElseThrow(()->new RuntimeException("Product Backlog not found"));
        Epic epic= epicService.findById(epicId);
        //hna ma3ytnach 3la attachepicprovidid
        // hit 3tana epic fin khassna ncree userstory
        if (! Objects.equals(epicId,productBacklogId)){
            throw new RuntimeException("Epic does not belong to the given ProductBacklog");
        }
        // 4) valider champs obligatoires
        validateUserStory(userStory);

        // 5) forcer les liens (le PATH décide)
        userStory.setEpic(epic);
        userStory.setProductBacklog(productBacklog);

        return userStoryRepository.save(userStory);

    }

    @Override
    public List<UserStory> findAll() {
        return userStoryRepository.findAll();
    }

    @Override
    public UserStory findById(Long id){
        return userStoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("UserStory not found with id " + id));
    }

    @Override
    public List<UserStory> findByProductBacklog(Long productbacklogId){

        //doit verifier si ce productbacklog existe
        productBacklogRepository.findById(productbacklogId);
        return userStoryRepository.findByProductBacklogId(productbacklogId);
    }

    @Override
    public List<UserStory> findByEpic(Long epicId){

        epicService.findById(epicId);
        return userStoryRepository.findByEpicId(epicId);
    }

    @Override
    public List<UserStory> findByPriority(MoSCoW priority){
        if(priority == null){
            throw new RuntimeException("priority is required");
        }
        return userStoryRepository.findByPriority(priority);
    }

    @Override
    public List<UserStory> findByStatus(Status status){
        if(status == null){
            throw new RuntimeException("status is required");
        }
        return userStoryRepository.findByStatus(status);
    }

    @Override
    public UserStory update(Long id, UserStory userStory){
        //userstory doit exister
        // 1) vérifier existence
        //UserStory existing = userStoryRepository.findById(id); hena l9it mochkil f findById
        // hit type de retour kaykon optional  bhala laglty makatgerech exception
        UserStory existing = userStoryRepository.findById(id).orElseThrow(() -> new RuntimeException("UserStory not found with id " + id));

        //UserStory existing = userStoryService.findById(id);
        validateUserStory(userStory);

        // 4) mise à jour des champs autorisés (sans toucher aux relations)
        existing.setTitle(userStory.getTitle());
        existing.setDescription(userStory.getDescription());
        //existing.setAcceptanceCriteria(userStory.getAcceptanceCriteria());
        existing.setPriority(userStory.getPriority());
        existing.setStatus(userStory.getStatus());

        // 5) save
        return userStoryRepository.save(existing);


    }

    @Override
    public void delete(Long id) {
        // 1) vérifier existence
        UserStory existing = userStoryRepository.findById(id).orElseThrow(() -> new RuntimeException("UserStory not found with id " + id));

        // 2) supprimer
        userStoryRepository.delete(existing);
    }

}
