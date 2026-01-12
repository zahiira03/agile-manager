package com.agilemanager;

import com.agilemanager.entities.ProductBacklog;
import com.agilemanager.repository.ProductBacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgileManagerApplication  {
    public static void main(String[] args) {
        SpringApplication.run(AgileManagerApplication.class, args);
    }
}


    //@Autowired
    // private ProductBacklogRepository productBacklogRepository;


    //public static void main(String[] args) {
        //si je demarre cette apllication spring va demarrer
        //SpringApplication.run(AgileManagerApplication.class, args);
        //}

    //@Override
    //public void run(String... args) throws Exception {

        //ProductBacklog pb = new ProductBacklog(1L, "name", "des");
        //productBacklogRepository.save(pb);


    //}
//}
