package com.jalba.proyecto_metamask_back.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;



import com.jalba.proyecto_metamask_back.db.entities.Sorteo;


public interface SorteoRepository extends MongoRepository<Sorteo, String> {

}