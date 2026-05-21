package com.example.cadastrodepalestras.service;

import com.example.cadastrodepalestras.model.Evento;
import com.example.cadastrodepalestras.model.Palestra;
import com.example.cadastrodepalestras.model.Palestrante;
import com.example.cadastrodepalestras.repository.EventoRepository;
import com.example.cadastrodepalestras.repository.PalestraRepository;
import com.example.cadastrodepalestras.repository.PalestranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalestraService {

    private final PalestraRepository palestraRepository;
    private final PalestranteRepository palestranteRepository;
    private final EventoRepository eventoRepository;

    public PalestraService(PalestraRepository palestraRepository, PalestranteRepository palestranteRepository, EventoRepository eventoRepository) {
        this.palestraRepository = palestraRepository;
        this.palestranteRepository = palestranteRepository;
        this.eventoRepository = eventoRepository;
    }

    public Palestra salvarPalestra(Palestra palestra) {

        Evento eventoCompleto =
                eventoRepository.findById(palestra.getEvento().getId())
                        .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        palestra.setEvento(eventoCompleto);

        List<Palestrante> palestrantesCompletos =
                palestra.getPalestrantes()
                        .stream()
                        .map(p -> palestranteRepository.findById(p.getId())
                                .orElseThrow(() -> new RuntimeException("Palestrante não encontrado")))
                        .toList();

        palestra.setPalestrantes(palestrantesCompletos);

        return palestraRepository.save(palestra);
    }

    public List<Palestra> getAllPalestras() {
        return palestraRepository.findAll();
    }

    public Optional<Palestra> updatePalestra(Long id, Palestra newPalestraData) {
        return palestraRepository.findById(id).map(palestra -> {
            palestra.setNome(newPalestraData.getNome());
            return palestraRepository.save(palestra);
        });
    }

    @Transactional
    public boolean deletePalestra(Long id) {

        Optional<Palestra> optional = palestraRepository.findById(id);

        if (optional.isPresent()) {

            Palestra palestra = optional.get();

            if (palestra.getEvento() != null) {
                palestra.getEvento().getPalestras().remove(palestra);
            }

            for (Palestrante palestrante : palestra.getPalestrantes()) {
                palestrante.getPalestras().remove(palestra);
            }

            palestra.getPalestrantes().clear();

            palestraRepository.delete(palestra);

            return true;
        }

        return false;
    }
}