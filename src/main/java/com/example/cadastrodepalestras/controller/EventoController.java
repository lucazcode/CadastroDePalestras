package com.example.cadastrodepalestras.controller;

import com.example.cadastrodepalestras.model.Evento;
import com.example.cadastrodepalestras.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastropalestras")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/eventos")
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        Evento eventoCriada = eventoService.salvarEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoCriada);
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<Evento>> allEventos() {
        List<Evento> eventos = eventoService.getAllEventos();
        return ResponseEntity.ok(eventos);
    }

    @PutMapping("/eventos/{id}")
    public ResponseEntity<Evento> updateUser(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.updateEvento(id, evento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        boolean deleted = eventoService.deleteEvento(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}