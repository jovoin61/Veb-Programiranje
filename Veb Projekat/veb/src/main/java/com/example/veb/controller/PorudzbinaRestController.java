package com.example.veb.controller;

import com.example.veb.dto.*;
import com.example.veb.model.*;
import com.example.veb.repository.KpgsRepository;
import com.example.veb.repository.MenadzerRepository;
import com.example.veb.repository.RestoranRepository;
import com.example.veb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PorudzbinaRestController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private PorudzbinaService porudzbinaService;
    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private ArtikalService artikalService;
    @Autowired
    private KpgsService kpgsService;
    @Autowired
    private KpgsRepository kpgsRepository;
    @Autowired
    private MenadzerService menadzerService;
    @Autowired
    private DostavljacService dostavljacService;
    @Autowired
    private KupacService kupacService;



    @GetMapping("/porudzbine/kupac")
    public ResponseEntity<List<PorudzbinaDto>> ispisi_porudzbine_kupac(HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)){

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
            return new ResponseEntity<List<PorudzbinaDto>>(porudzbinaService.prikaz_svih_porudzbina_korisnik(korisnik), HttpStatus.OK);
        }
        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/porudzbine/dostavljac")
    public ResponseEntity<List<PorudzbinaDto>> ispisi_porudzbine_dostavljac(HttpSession session){

        if( sessionService.da_li_je_korisnik(Uloga.DOSTAVLJAC, session)){
            Dostavljac dostavljac = (Dostavljac)session.getAttribute("korisnik");
            return new ResponseEntity<List<PorudzbinaDto>>(porudzbinaService.prikaz_svih_porudzbina_dostavljac(dostavljac),HttpStatus.OK);

        }
        return new ResponseEntity("Niste Dostavljac", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/menadzer/restoran/porudzbine")
    public ResponseEntity<List<PorudzbinaDto>> ispisi_porudzbine_menadzer(HttpSession session){

        if( sessionService.da_li_je_korisnik(Uloga.MENADZER, session)){
            Menadzer menadzer = (Menadzer) session.getAttribute("korisnik");
            return new ResponseEntity<List<PorudzbinaDto>>(porudzbinaService.prikaz_svih_porudzbina_menadzer(menadzer),HttpStatus.OK);

        }
        return new ResponseEntity("Niste Menadzer", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/korpa/dodaj/restoran/{id}")
    public ResponseEntity<String> dodajUKorpu(@PathVariable(name = "id") Long idRestorana, @RequestBody KorpaDto korpaDto, HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)){

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

            Restoran restoran = restoranRepository.getById(idRestorana);
            if(restoran.getStatusRestorana() == StatusRestorana.NE_RADI){
                return new ResponseEntity("Restoran ne radi!", HttpStatus.BAD_REQUEST);
            }

            Artikal a = artikalService.getArtikalByNaziv(korpaDto.getNazivArtikla(), idRestorana);
            if(a == null)
                return new ResponseEntity("U restoranu ne postoji artikal! ", HttpStatus.BAD_REQUEST);


            if(kpgsService.dodajUKorpu(a, korisnik.getId(), korpaDto.getKolicina(), idRestorana))
                return ResponseEntity.ok("Uspesno ubaceno u korpu.");
            return new ResponseEntity("Neispravna kolicina", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/korpa/izbaci/{idKpgs}/restoran/{idR}")
    public ResponseEntity<String> izbaciIzKorpe(@PathVariable(name = "idR") Long idRestorana, @PathVariable(name = "idKpgs") Long idKpgs, HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)){

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");


            if(kpgsService.obrisiStavku(korisnik.getId(), idKpgs))
                return new ResponseEntity("Uspesno brisanje", HttpStatus.OK);

            return new ResponseEntity("Doslo je do greske..", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/korpa/pregled/restoran/{idR}")
    public ResponseEntity<KorpaCenaIspis> pregledKorpe(@PathVariable(name = "idR") Long idRestorana , HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)) {

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

            List<Kpgs> kpgs = kpgsService.getTrenutnaKorpa(korisnik.getId());
            if(kpgs.isEmpty())
                return new ResponseEntity("Korpa je prazna", HttpStatus.BAD_REQUEST);

            List<KorpaIspisDto> korpaIspisDtoList = new ArrayList<>();
            for (Kpgs k : kpgs){
                KorpaIspisDto temp = new KorpaIspisDto(k);
                korpaIspisDtoList.add(temp);
            }
            Double ukupnaCena = kpgsService.ukupnaCenaStavki(kpgs);
            KorpaCenaIspis ispis = new KorpaCenaIspis(korpaIspisDtoList,ukupnaCena);
            return new ResponseEntity<KorpaCenaIspis>(ispis, HttpStatus.OK);


        }
        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/korpa/smanji/{idKpgs}/restoran/{idR}")
    public ResponseEntity<String> smanjiKolicinu(@PathVariable(name = "idR") Long idRestoran, @PathVariable(name = "idKpgs") Long idKpgs, HttpSession session) {

        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)) {

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
            Kpgs korpa = kpgsRepository.getById(idKpgs);

            if (korpa == null)
                return new ResponseEntity("Doslo je do greske..", HttpStatus.BAD_REQUEST);


            if(korisnik.getId() != korpa.getKupac().getId())
                return new ResponseEntity("Nije vasa korpa", HttpStatus.BAD_REQUEST);


            if (kpgsService.smanjiKolicinu(idKpgs))
                return ResponseEntity.ok("Uspesno smanjena je kolicina za jedan");
            return new ResponseEntity("Kolicina ne sme biti manja od jedan", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/korpa/poruci/restoran/{idR}")
    public ResponseEntity<String> poruci(@PathVariable(name = "idR") Long idRestorana, HttpSession session){

        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)) {

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");


            if (kpgsService.poruci(korisnik.getId()))
                return ResponseEntity.ok("Uspesna Porudzbina");
            return new ResponseEntity("Korpa je prazna", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/menadzer/porudzbina/{idP}")
    public ResponseEntity<String> promeniStatusPorudzbineMenadzer(@PathVariable(name = "idP") Long idPorudzbine, HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.MENADZER, session)) {

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

            if(menadzerService.promeniStatusPorudzbine(idPorudzbine))
                return ResponseEntity.ok("Promena Statusa");
            return new ResponseEntity("Porudzbina nema odgovarajuci status", HttpStatus.BAD_REQUEST);


        }
        return new ResponseEntity("Niste Menadzer", HttpStatus.FORBIDDEN);
    }
    @PutMapping("/dostavljac/porudzbina/{idP}")
    public ResponseEntity<String> promeniStatusPorudzbineDostavljac(@PathVariable(name = "idP") Long idPorudzbine, HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.DOSTAVLJAC, session)) {

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

            if(dostavljacService.promeniStatusPorudzbine(idPorudzbine,korisnik.getId()))
                return ResponseEntity.ok("Promena Statusa");
            return new ResponseEntity("Porudzbina nema odgovarajuci status", HttpStatus.BAD_REQUEST);


        }
        return new ResponseEntity("Niste Dostavljac", HttpStatus.FORBIDDEN);
    }
    @PostMapping("/kupac/komentar/porudzbina/{idP}")
    public ResponseEntity<String> postaviKomentar(@PathVariable(name = "idP") Long idPorudzbine, @RequestBody KomentarNoviDto komentar, HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)) {

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

            if(kupacService.postaviKomentar(idPorudzbine,korisnik,komentar))
                return ResponseEntity.ok("Postavljen Komentar");
            return new ResponseEntity("Pogresan zahtav", HttpStatus.BAD_REQUEST);


        }
        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

}

