package com.example.cadastrodepalestras.service;

import com.example.cadastrodepalestras.model.Organizador;
import com.example.cadastrodepalestras.repository.OrganizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    private final OrganizadorRepository organizadorRepository;

    public OrganizadorService(OrganizadorRepository organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    public Organizador salvarOrganizador(Organizador organizador) {
        return organizadorRepository.save(organizador);
    }

    public List<Organizador> getAllOrganizadores() {
        return organizadorRepository.findAll();
    }

    public Optional<Organizador> updateOrganizador(Long id, Organizador newOrganizadorData) {
        return organizadorRepository.findById(id).map(organizador -> {
            organizador.setNome(newOrganizadorData.getNome());
            return organizadorRepository.save(organizador);
        });
    }

    public boolean deleteOrganizador(Long id) {
        if (organizadorRepository.existsById(id)) {
            organizadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}