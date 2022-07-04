package com.example.veb.service;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Menadzer;
import com.example.veb.model.Porudzbina;
import com.example.veb.model.Status;
import com.example.veb.repository.MenadzerRepository;
import com.example.veb.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private AdminService adminService;

    public String dodaj_menadzera(Korisnik korisnik){
        if(!adminService.dvaKorisnickaImena(korisnik.getKorisnickoIme())) return "KORISNICKO IME VEC POSTOJI!";

        Menadzer noviMenadzer = new Menadzer(korisnik);
        menadzerRepository.save(noviMenadzer);

        return "DODAT NOVI MENADZER";
    }

    public boolean promeniStatusPorudzbine(Long id){
        Optional<Porudzbina> porudzbina1 = porudzbinaRepository.findById(id);
        if(!porudzbina1.isPresent()){
            return false;
        }

        Porudzbina porudzbina = porudzbinaRepository.getByUuid(id);

        if(porudzbina.getStatus()== Status.OBRADA) {
            porudzbina.setStatus(Status.U_PRIPREMI);
            porudzbinaRepository.save(porudzbina);
            return true;
        }
        if(porudzbina.getStatus()== Status.U_PRIPREMI) {
            porudzbina.setStatus(Status.CEKA_DOSTAVLJACA);
            porudzbinaRepository.save(porudzbina);
            return true;
        }

        return false;
    }

}
