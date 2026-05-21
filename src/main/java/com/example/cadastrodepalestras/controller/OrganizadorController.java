package com.example.cadastrodepalestras.controller;

import com.example.cadastrodepalestras.model.Organizador;
import com.example.cadastrodepalestras.service.OrganizadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastropalestras")
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    @GetMapping("/organizadores")
    public ResponseEntity<List<Organizador>> allOrganizadores() {
        List<Organizador> organizadores = organizadorService.getAllOrganizadores();
        return ResponseEntity.ok(organizadores);
    }

    @PostMapping("/organizadores")
    public ResponseEntity<Organizador> criarOrganizador(@RequestBody Organizador organizador) {
        Organizador organizadorCriada = organizadorService.salvarOrganizador(organizador);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizadorCriada);
    }

    @PutMapping("/organizadores/{id}")
    public ResponseEntity<Organizador> updateUser(@PathVariable Long id, @RequestBody Organizador organizador) {
        return organizadorService.updateOrganizador(id, organizador)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/organizadores/{id}")
    public ResponseEntity<Void> deleteOrganizador(@PathVariable Long id) {
        boolean deleted = organizadorService.deleteOrganizador(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}