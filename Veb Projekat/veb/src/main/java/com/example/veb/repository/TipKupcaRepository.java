package com.example.veb.repository;

import com.example.veb.model.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipKupcaRepository extends JpaRepository<TipKupca, Long> {
}
