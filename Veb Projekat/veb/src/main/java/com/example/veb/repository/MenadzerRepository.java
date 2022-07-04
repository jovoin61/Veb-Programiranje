package com.example.veb.repository;

import com.example.veb.model.Menadzer;
import com.example.veb.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Long> {
    public Menadzer getById(Long id);
    public Menadzer findByKorisnickoIme(String korisnickoIme);
    public Menadzer getByRestoran(Restoran restoran);
}
