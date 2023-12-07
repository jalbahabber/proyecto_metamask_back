package com.jalba.proyecto_metamask_back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jalba.proyecto_metamask_back.db.entities.Participant;
import com.jalba.proyecto_metamask_back.db.services.ParticipantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/participants")
public class ParticipantsController {
    @Autowired
    private ParticipantService participantService;


    @PostMapping("/save")
    public ResponseEntity<String> saveNames(@RequestBody Participant p) {
        participantService.saveParticipant(p);
        return ResponseEntity.ok("Nombres guardados exitosamente");
    }

    @GetMapping("/giveId")
    public ResponseEntity<String> giveParticipantID() {
        
        return ResponseEntity.ok(participantService.devuelveId());
    }
    
}