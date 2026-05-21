package com.example.cadastrodepalestras.controller;

import com.example.cadastrodepalestras.model.Palestra;
import com.example.cadastrodepalestras.service.PalestraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastropalestras")
public class PalestraController {

    private final PalestraService palestraService;

    public PalestraController(PalestraService palestraService) {
        this.palestraService = palestraService;
    }

    @PostMapping("/palestras")
    public ResponseEntity<Palestra> criarPalestra(@RequestBody Palestra palestra) {
        Palestra palestraCriada = palestraService.salvarPalestra(palestra);
        return ResponseEntity.status(HttpStatus.CREATED).body(palestraCriada);
    }

    @GetMapping("/palestras")
    public ResponseEntity<List<Palestra>> allPalestras() {
        List<Palestra> palestras = palestraService.getAllPalestras();
        return ResponseEntity.ok(palestras);
    }

    @PutMapping("/palestras/{id}")
    public ResponseEntity<Palestra> updateUser(@PathVariable Long id, @RequestBody Palestra palestra) {
        return palestraService.updatePalestra(id, palestra)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/palestras/{id}")
    public ResponseEntity<Void> deletePalestra(@PathVariable Long id) {
        boolean deleted = palestraService.deletePalestra(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}