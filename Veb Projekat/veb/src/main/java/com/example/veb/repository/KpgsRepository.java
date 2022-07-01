package com.example.veb.repository;

import com.example.veb.model.Kpgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface KpgsRepository extends JpaRepository<Kpgs, Long> {

}
