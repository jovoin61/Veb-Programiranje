package com.example.veb.service;

import com.example.veb.dto.KomentarNoviDto;
import com.example.veb.model.*;
import com.example.veb.repository.KomentarRepository;
import com.example.veb.repository.KupacRepository;
import com.example.veb.repository.PorudzbinaRepository;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KupacService {
    @Autowired
    private KupacRepository kupacRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private KomentarRepository komentarRepository;
    @Autowired
    private RestoranRepository restoranRepository;


    public Kupac findKupac(Korisnik korisnik){
        Kupac kupac = kupacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
        return kupac;
    }

    public boolean postaviKomentar(Long idPorudzbina, Korisnik korisnik, KomentarNoviDto komentarNoviDto){
        Porudzbina p = porudzbinaRepository.getByUuid(idPorudzbina);

        Optional<Porudzbina> porudzbina = porudzbinaRepository.findById(idPorudzbina);

        if(!porudzbina.isPresent())
            return false;

        if(korisnik.getId() != p.getKupac().getId()) return false;

        if(p.getStatus() != Status.DOSTAVLJENA) return false;


        Komentar komentar = new Komentar(p.getKupac(),p.getRestoran(), komentarNoviDto.getKomentar(), komentarNoviDto.getOcena());
        p.getRestoran().getKomentari().add(komentar);
        restoranRepository.save(p.getRestoran());
        komentarRepository.save(komentar);

        return true;
    }
}

