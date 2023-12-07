package com.jalba.proyecto_metamask_back.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jalba.proyecto_metamask_back.db.entities.Sorteo;
import com.jalba.proyecto_metamask_back.db.services.SorteoService;

@RestController
public class TemporizerController {

    // private String[] names;
    @Autowired
    SorteoService sorteoService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/temporizer")
    public void temporize() {
        String id = sorteoService.createSorteo();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        System.out.println(id);
        Runnable chooseWinnerTask = () -> {
            String winner = null;
            Sorteo s = sorteoService.findSorteo(id).get();
            String[] participants = s.getParticipants();
            if (participants != null && participants.length != 0) {

                Random random = new Random();
                int winnerIndex = random.nextInt(participants.length - 1);
                winner = participants[winnerIndex];
            }
            s.setWinner(winner);
            sorteoService.saveSorteo(s);
        };
        executorService.schedule(chooseWinnerTask, 60, TimeUnit.SECONDS);
    }

}