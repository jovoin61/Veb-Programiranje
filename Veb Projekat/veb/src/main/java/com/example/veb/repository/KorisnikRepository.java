package com.example.veb.repository;

import com.example.veb.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    public Korisnik getById(Long id);
    public Korisnik getByKorisnickoIme(String korisnicko_ime);
}
