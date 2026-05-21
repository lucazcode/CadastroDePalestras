package com.example.cadastrodepalestras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cadastrodepalestras.model.Palestrante;

@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {
}