package com.jalba.proyecto_metamask_back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jalba.proyecto_metamask_back.db.entities.Sorteo;
import com.jalba.proyecto_metamask_back.db.services.SorteoService;

@RestController
@RequestMapping("/sorteo")
public class SorteoController {
    @Autowired
    private SorteoService sorteoService;


    @PostMapping("/create")
    public ResponseEntity<String> createDraw() {
        sorteoService.createSorteo();
        return ResponseEntity.ok("Sorteo guardados exitosamente");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Sorteo>> getAllDraws() {
        sorteoService.getAllSorteos();
        return ResponseEntity.ok(sorteoService.getAllSorteos());
    }

     @GetMapping("/{sorteoId}/ganador")
    public ResponseEntity<String> obtenerGanadorDelSorteo(@PathVariable String sorteoId) {

        String ganador = sorteoService.obtenerGanadorPorId(sorteoId);
            return ResponseEntity.ok(ganador);
    }
}