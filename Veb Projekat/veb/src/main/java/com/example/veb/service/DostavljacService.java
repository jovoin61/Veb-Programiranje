package com.example.veb.service;

import com.example.veb.model.*;
import com.example.veb.repository.DostavljacRepository;
import com.example.veb.repository.KupacRepository;
import com.example.veb.repository.MenadzerRepository;
import com.example.veb.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DostavljacService {
    @Autowired
    private DostavljacRepository dostavljacRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private KupacRepository kupacRepository;
    @Autowired
    private AdminService adminService;


    public String dodaj_dostavljaca(Korisnik korisnik){

        if(!adminService.dvaKorisnickaImena(korisnik.getKorisnickoIme())) return "KORISNICKO IME VEC POSTOJI!";

        Dostavljac noviDostavljac = new Dostavljac(korisnik);
        dostavljacRepository.save(noviDostavljac);

        return "DODAT NOVI DOSTAVLJAC";
    }

    public boolean promeniStatusPorudzbine(Long idP,Long idD){
        Porudzbina porudzbina = porudzbinaRepository.getByUuid(idP);
        Dostavljac dostavljac = dostavljacRepository.getById(idD);
        if(porudzbina.getStatus()== Status.CEKA_DOSTAVLJACA) {
            porudzbina.setStatus(Status.U_TRANSPORTU);
            dostavljac.getPorudzbine().add(porudzbina);

            dostavljacRepository.save(dostavljac);
            porudzbinaRepository.save(porudzbina);
            return true;
        }
        if(porudzbina.getStatus()== Status.U_TRANSPORTU) {

            porudzbina.setStatus(Status.DOSTAVLJENA);
            dostavljac.getPorudzbine().add(porudzbina);

            Double bodovi = porudzbina.getCena() / 1000 * 133 ;
            porudzbina.getKupac().setBrojSakupljenihBodova(bodovi);

            kupacRepository.save(porudzbina.getKupac());
            dostavljacRepository.save(dostavljac);
            porudzbinaRepository.save(porudzbina);
            return true;
        }

        return false;
    }
}
