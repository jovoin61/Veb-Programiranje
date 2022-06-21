package com.example.veb.repository;

import com.example.veb.model.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
    public Lokacija getByAdresa(String adresa);
}
