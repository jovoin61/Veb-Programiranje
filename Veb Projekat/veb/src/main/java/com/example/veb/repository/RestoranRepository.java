package com.example.veb.repository;

import com.example.veb.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long> {
    public Restoran getById(Long id);
    public Restoran findByNaziv(String naziv);
    public Restoran getByLokacija(Lokacija lokacija);
    public Set<Restoran> findByTip(String tip);
    public Restoran getByArtikli(Artikal artikal);

}
