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

    @Autowired
    SorteoService sorteoService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/temporizer")
    public void temporize() {
        String id = sorteoService.createSorteo();
        System.out.println(id);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable chooseWinnerTask = () -> {
            try {
                Sorteo s = sorteoService.findSorteo(id).orElseThrow(() -> new RuntimeException("Sorteo no encontrado"));
                List<String> participants = s.getParticipants();

                if (participants != null && !participants.isEmpty()) {
                    Random random = new Random();
                    int winnerIndex = random.nextInt(participants.size());
                    String winner = participants.get(winnerIndex);
                    s.setWinner(winner);
                }

                s.setEnded(true);
                sorteoService.saveSorteo(s);
            } catch (Exception e) {

            }
        };

        executorService.schedule(chooseWinnerTask, 30, TimeUnit.SECONDS);
    }
}