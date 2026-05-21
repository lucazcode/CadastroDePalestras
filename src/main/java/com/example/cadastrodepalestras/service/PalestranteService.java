package com.example.cadastrodepalestras.service;

import com.example.cadastrodepalestras.model.Palestrante;
import com.example.cadastrodepalestras.repository.PalestranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalestranteService {

    private final PalestranteRepository palestranteRepository;

    public PalestranteService(PalestranteRepository palestranteRepository) {
        this.palestranteRepository = palestranteRepository;
    }

    public Palestrante salvarPalestrante(Palestrante palestrante) {
        return palestranteRepository.save(palestrante);
    }

    public List<Palestrante> getAllPalestrantes() {
        return palestranteRepository.findAll();
    }

    public Optional<Palestrante> updatePalestrante(Long id, Palestrante newPalestranteData) {
        return palestranteRepository.findById(id).map(palestrante -> {
            palestrante.setNome(newPalestranteData.getNome());
            return palestranteRepository.save(palestrante);
        });
    }

    public boolean deletePalestrante(Long id) {
        if (palestranteRepository.existsById(id)) {
            palestranteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}