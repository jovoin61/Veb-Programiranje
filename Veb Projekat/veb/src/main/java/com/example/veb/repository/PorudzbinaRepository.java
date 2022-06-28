package com.example.veb.repository;

import com.example.veb.model.Kupac;
import com.example.veb.model.Porudzbina;
import com.example.veb.model.Restoran;
import com.example.veb.model.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {
    public Porudzbina getByUuid(UUID uuid);
    public Set<Porudzbina> getByRestoran(Restoran restoran);
    public Set<Porudzbina> getByKupac(Kupac kupac);
}
