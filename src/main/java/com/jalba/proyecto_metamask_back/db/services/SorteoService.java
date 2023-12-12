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
import static com.mongodb.client.model.Filters.eq;
import java.util.*;

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
        List<String> emptyList = new ArrayList<>();
        s.setEnded(false);
        MongoClient mc = MongoClients.create(url);
        MongoDatabase mdb = mc.getDatabase("metamask_app");
        MongoCollection mcol = mdb.getCollection("draws");
        Document doc = new Document("winner", s.getWinner())
                .append("participants", emptyList)
                .append("ended", s.getEnded());
        InsertOneResult result = mcol.insertOne(doc);

        String insertedId = result.getInsertedId().asObjectId().getValue().toString();
        return insertedId;
    }

    public void saveSorteo(Sorteo s) {
        MongoClient mc = MongoClients.create(url);
        MongoDatabase mdb = mc.getDatabase("metamask_app");
        MongoCollection<Document> mcol = mdb.getCollection("draws");
        
        Document updateFields = new Document();
        updateFields.append("winner", s.getWinner());
        updateFields.append("ended", s.getEnded());
        
        Document updateQuery = new Document("$set", updateFields);
        
        mcol.findOneAndUpdate(eq("_id", new ObjectId(s.getId())), updateQuery);
    }


    public List<Sorteo> getAllSorteos() {
        return sorteoRepository.findAll();
    }

    public Optional<Sorteo> findSorteo(String id) {
        Optional<Sorteo> s = sorteoRepository.findById(id);
        return s;

    }


    public String obtenerGanadorPorId(String sorteoId) {
        Sorteo sorteo = sorteoRepository.findById(sorteoId).get();

        if (sorteo != null) {
            return sorteo.getWinner();
            
        } else {
            return null;
        }
        
    }

}
