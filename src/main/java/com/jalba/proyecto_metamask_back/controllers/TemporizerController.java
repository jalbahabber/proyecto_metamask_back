package com.jalba.proyecto_metamask_back.controllers;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemporizerController {

    private String[] names;
    //private String winner="";
    @CrossOrigin(origins = "http://localhost:3000")
     @PostMapping("/temporizer")
    public CompletableFuture<ResponseEntity<String>> temporize(@RequestBody String[] names_) {
        names = names_;

        CompletableFuture<String> futureWinner = new CompletableFuture<>();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable chooseWinnerTask = () -> {
            String winner = "";
            if (names.length != 0) {
                Random random = new Random();
                int winnerIndex = random.nextInt(names.length);
                winner = names[winnerIndex];
                System.out.println("El ganador es: " + winner);
            }
            futureWinner.complete(winner);
        };

        executorService.schedule(chooseWinnerTask, 5, TimeUnit.SECONDS);

        return futureWinner.thenApply(winner -> ResponseEntity.status(200).body(winner));
    }
}