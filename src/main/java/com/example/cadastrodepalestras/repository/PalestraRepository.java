package com.example.cadastrodepalestras.repository;

import com.example.cadastrodepalestras.model.Palestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalestraRepository extends JpaRepository<Palestra, Long> {

}