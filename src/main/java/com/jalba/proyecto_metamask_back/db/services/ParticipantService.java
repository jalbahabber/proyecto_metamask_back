package com.jalba.proyecto_metamask_back.db.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jalba.proyecto_metamask_back.db.entities.Participant;
import com.jalba.proyecto_metamask_back.db.repositories.ParticipantRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class ParticipantService {
   @Value("${spring.data.mongodb.uri}")
   private String url;

   @Autowired
   private ParticipantRepository participantRepository;

   public void saveParticipant(Participant p) {
      // nameRepository.save(name);
      MongoClient mc = MongoClients.create(url);
      MongoDatabase mdb = mc.getDatabase("metamask_app");
      MongoCollection mcol = mdb.getCollection("participants");
      Document doc = new Document("id", p.getId())
            .append("wallet", p.getWallet())
            .append("name", p.getName());
      mcol.insertOne(doc);

   }

   public String devuelveId() {
      List<Participant> participants = participantRepository.findAll();

      if (!participants.isEmpty()) {
         Participant ultimoParticipante = participants.get(participants.size() - 1);
         int ultimoId = Integer.parseInt(ultimoParticipante.getId());
         ;
         int nuevoId = ultimoId + 1;

         return String.valueOf(nuevoId);
      } else {

         return "1";
      }

   }
}