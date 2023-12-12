package com.jalba.proyecto_metamask_back.db.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "draws")
public class Sorteo {

    @Id
    public String id;

    public String winner;

    public Boolean ended;

    public List<String> participants;
    
}
