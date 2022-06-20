package com.example.veb.repository;

import com.example.veb.model.Komentar;
import com.example.veb.model.Kupac;
import com.example.veb.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    //public Set<Komentar> getByRestoran(Restoran restoran);
    //public Set<Komentar> getByRestoran(Kupac kupac);
}
