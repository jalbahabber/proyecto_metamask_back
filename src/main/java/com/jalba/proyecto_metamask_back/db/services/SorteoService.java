package com.jalba.proyecto_metamask_back.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jalba.proyecto_metamask_back.db.entities.Sorteo;
import com.jalba.proyecto_metamask_back.db.repositories.SorteoRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;

@Service
public class SorteoService {
    @Value("${spring.data.mongodb.uri}")
    private String url;

    @Autowired
    private SorteoRepository sorteoRepository;

    public String createSorteo() {
        // nameRepository.save(name);
        Sorteo s = new Sorteo();
        s.setEnded(false);
        MongoClient mc = MongoClients.create(url);
        MongoDatabase mdb = mc.getDatabase("metamask_app");
        MongoCollection mcol = mdb.getCollection("draws");
        Document doc = new Document("winner", s.getWinner())
                .append("participants", s.getParticipants())
                .append("ended", s.getEnded());
          InsertOneResult result = mcol.insertOne(doc);

    String insertedId = result.getInsertedId().asObjectId().getValue().toString();
        return insertedId;
    }

        public String saveSorteo(Sorteo s) {
    
        MongoClient mc = MongoClients.create(url);
        MongoDatabase mdb = mc.getDatabase("metamask_app");
        MongoCollection mcol = mdb.getCollection("draws");
        Document doc = new Document("winner", s.getWinner())
                .append("participants", s.getParticipants())
                .append("ended", s.getEnded());
        InsertOneResult result = mcol.insertOne(doc);

        String insertedId = result.getInsertedId().toString();

        return insertedId;
    }

    public List<Sorteo> getAllSorteos() {
        return sorteoRepository.findAll();
    }

    public Optional<Sorteo> findSorteo(String id){
        Optional<Sorteo> s = sorteoRepository.findById(id);
        return s;

    }

}