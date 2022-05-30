package com.example.veb.repository;

import com.example.veb.model.Dostavljac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac, Long> {
}
