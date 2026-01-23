package com.agilemanager.repository;

import com.agilemanager.entities.ProductBacklog;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
    void delete(ProductBacklog productBacklog);
    //Je dois gérer une table product_backlog dont la clé primaire est de type Long.

}
