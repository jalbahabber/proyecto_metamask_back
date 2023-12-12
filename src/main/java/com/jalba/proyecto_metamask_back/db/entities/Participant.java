package com.jalba.proyecto_metamask_back.db.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "participants")
public class Participant {

    @Id
    private String id;

    private String number;

    public String name;

    public String wallet;

    public String sorteo;
}
