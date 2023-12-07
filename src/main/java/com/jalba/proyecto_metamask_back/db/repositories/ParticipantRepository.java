package com.jalba.proyecto_metamask_back.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jalba.proyecto_metamask_back.db.entities.Participant;



public interface ParticipantRepository extends MongoRepository<Participant, String> {

}
