package com.example.veb.repository;

import com.example.veb.model.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
    public Artikal getById(Long id);
}
