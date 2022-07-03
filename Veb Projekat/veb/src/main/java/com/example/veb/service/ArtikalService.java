package com.example.veb.service;

import com.example.veb.model.Artikal;
import com.example.veb.model.Restoran;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ArtikalService {

    @Autowired
    private RestoranRepository restoranRepository;


    public Artikal getArtikalByNaziv(String naziv, Long idRestorana){
        Restoran r = restoranRepository.getById(idRestorana);
        Set<Artikal> artikals = r.getArtikli();
        for(Artikal a : artikals){
            if(a.getNaziv().equals(naziv))
                return a;
        }
        return null;
    }
}
