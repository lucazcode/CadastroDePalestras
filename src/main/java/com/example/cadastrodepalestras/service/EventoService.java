package com.example.cadastrodepalestras.service;

import com.example.cadastrodepalestras.model.Evento;
import com.example.cadastrodepalestras.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> updateEvento(Long id, Evento newEventoData) {
        return eventoRepository.findById(id).map(evento -> {
            evento.setNome(newEventoData.getNome());
            return eventoRepository.save(evento);
        });
    }

    public boolean deleteEvento(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}