package com.example.veb.service;

import com.example.veb.model.*;
import com.example.veb.repository.KpgsRepository;
import com.example.veb.repository.KupacRepository;
import com.example.veb.repository.PorudzbinaRepository;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class KpgsService {

    @Autowired
    private KupacRepository kupacRepository;
    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private KpgsRepository kpgsRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;


    public boolean dodajUKorpu(Artikal artikal, Long idKupac, int kolicina, Long idRestoran){
        Kupac kupac = kupacRepository.getById(idKupac);
        if(kolicina < 1)
            return false;
        Restoran r = restoranRepository.getById(idRestoran);

        List<Kpgs> trenutnaKorpa = getTrenutnaKorpa(idKupac);
        for(Kpgs k : trenutnaKorpa){
            if(k.getRestoran().getId() != idRestoran)
                return false;
        }

        Kpgs k = new Kpgs(artikal, kolicina*artikal.getCena(), kolicina, kupac,r);
        kpgsRepository.save(k);
        return true;
    }

    public List<Kpgs> getTrenutnaKorpa(Long idKupac){
        List<Kpgs> korpa = kpgsRepository.findAll();
        List<Kpgs> trenutnaKorpa = new ArrayList<>();

        for(Kpgs k : korpa){
                if (k.getKupac().getId() == idKupac && k.getPorudzbina() == null)
                    trenutnaKorpa.add(k);

        }
        return trenutnaKorpa;
    }

    public boolean obrisiStavku(Long idKupac, Long idKorpa){

        Optional<Kpgs> findK = kpgsRepository.findById(idKorpa);
        if(!findK.isPresent())
            return false;

        Kpgs kpgs = kpgsRepository.getById(idKorpa);

        if(kpgs == null) {
            return false;
        }

        if(kpgs.getKupac().getId() != idKupac){
            return false;
        }
        kpgs.setArtikal(null);
        kpgsRepository.delete(kpgs);
        return true;
    }
    public Double ukupnaCenaStavki(List<Kpgs> stavke) {
        Double cena = 0.0;
        for(Kpgs pa : stavke)
            cena += pa.getArtikal().getCena()*pa.getBrojArtikala();
        return cena;
    }
    public boolean smanjiKolicinu(Long id){
        Kpgs kpgs = kpgsRepository.getById(id);
        if(kpgs.getBrojArtikala()==1){
            return false;
        }
        kpgs.setBrojArtikala(kpgs.getBrojArtikala()-1);
        kpgs.setUkupnaCena(kpgs.getUkupnaCena()-kpgs.getArtikal().getCena());
        kpgsRepository.save(kpgs);
        return true;


    }

    public boolean poruci(Long id){

        Kupac kupac = kupacRepository.getById(id);
        Kpgs kpgs = new Kpgs();
        List<Kpgs> korpaList = getTrenutnaKorpa(id);
        Set<Kpgs> korpaSet = new HashSet<>();
        for (Kpgs k : korpaList){
            korpaSet.add(k);
            kpgs=k;
        }

        if(korpaList.isEmpty()) return false;

        Double ukupnaCena = ukupnaCenaStavki(korpaList);

        Date date = new Date();

        Porudzbina p = new Porudzbina(kpgs.getRestoran(),date,kupac,Status.OBRADA);
        p.setCena(ukupnaCena);
        p.setStavke(korpaSet);
        porudzbinaRepository.save(p);//Mozda treba ubaciti restoran

        Restoran r = kpgs.getRestoran();
        r.getPorudzbina().add(p);
        restoranRepository.save(r);

        for(Kpgs k : korpaList){
            k.setPorudzbina(p);
            kpgsRepository.save(k);
        }

        return true;
    }

}
