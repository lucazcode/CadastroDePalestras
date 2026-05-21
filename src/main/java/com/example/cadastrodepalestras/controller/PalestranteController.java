package com.example.cadastrodepalestras.controller;

import com.example.cadastrodepalestras.model.Palestrante;
import com.example.cadastrodepalestras.service.PalestranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastropalestras")
public class PalestranteController {

    private final PalestranteService palestranteService;

    public PalestranteController(PalestranteService palestranteService) {
        this.palestranteService = palestranteService;
    }

    @PostMapping("/palestrantes")
    public ResponseEntity<Palestrante> criarPalestrante(@RequestBody Palestrante palestrante) {
        Palestrante palestranteCriada = palestranteService.salvarPalestrante(palestrante);
        return ResponseEntity.status(HttpStatus.CREATED).body(palestranteCriada);
    }

    @GetMapping("/palestrantes")
    public ResponseEntity<List<Palestrante>> allPalestrantes() {
        List<Palestrante> palestrantes = palestranteService.getAllPalestrantes();
        return ResponseEntity.ok(palestrantes);
    }

    @PutMapping("/palestrantes/{id}")
    public ResponseEntity<Palestrante> updateUser(@PathVariable Long id, @RequestBody Palestrante palestrante) {
        return palestranteService.updatePalestrante(id, palestrante)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/palestrantes/{id}")
    public ResponseEntity<Void> deletePalestrante(@PathVariable Long id) {
        boolean deleted = palestranteService.deletePalestrante(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}