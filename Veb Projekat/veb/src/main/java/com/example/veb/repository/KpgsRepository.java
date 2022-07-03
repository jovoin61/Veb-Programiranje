package com.example.veb.repository;

import com.example.veb.model.Kpgs;
import com.example.veb.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KpgsRepository extends JpaRepository<Kpgs, Long> {
    public Kpgs getById(Long id);

}
