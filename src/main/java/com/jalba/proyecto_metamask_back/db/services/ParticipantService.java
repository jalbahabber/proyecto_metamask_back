package com.jalba.proyecto_metamask_back.db.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jalba.proyecto_metamask_back.db.entities.Participant;
import com.jalba.proyecto_metamask_back.db.repositories.ParticipantRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

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
      MongoCollection<Document> mcol = mdb.getCollection("participants");
      MongoCollection<Document> sorteoCol = mdb.getCollection("draws");
      
      Document participantDoc = new Document("number", p.getNumber())
          .append("wallet", p.getWallet())
          .append("name", p.getName())
          .append("sorteo", p.getSorteo());
      
      mcol.insertOne(participantDoc);
      Bson filter = Filters.eq("_id", new ObjectId(p.getSorteo()));
      Bson update = Updates.addToSet("participants", p.getNumber());
      sorteoCol.updateOne(filter, update);

   }

   public String devuelveId() {
      List<Participant> participants = participantRepository.findAll();

      if (!participants.isEmpty()) {
         Participant ultimoParticipante = participants.get(participants.size() - 1);
         String ultimoId = ultimoParticipante.getNumber();

         if (ultimoId == null || ultimoId.isEmpty()) {
            return "1";
         }

         int ultimoNumero = Integer.parseInt(ultimoId);
         int nuevoNumero = ultimoNumero + 1;
         return String.valueOf(nuevoNumero);
      } else {
         return "1";
      }

   }
}