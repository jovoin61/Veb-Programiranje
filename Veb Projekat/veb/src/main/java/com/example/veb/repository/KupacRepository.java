package com.example.veb.repository;

import com.example.veb.model.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long> {
    public Kupac findByKorisnickoIme(String korisnicko_ime);
    public Kupac getById(Long id);


}
