package com.example.veb.service;

import com.example.veb.dto.RegistracijaDto;
import com.example.veb.model.Korisnik;
import com.example.veb.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Service
public class KorisnikService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    KorisnikRepository korisnikRepository;

    public Korisnik izmena_profila(RegistracijaDto registracijaDto, Korisnik korisnik){

        if(registracijaDto.getKorisnickoIme() != null){
            korisnik.setKorisnickoIme(registracijaDto.getKorisnickoIme());
        }

        if(registracijaDto.getLozinka() != null){
            korisnik.setLozinka(registracijaDto.getLozinka());
        }

        if(registracijaDto.getIme() != null){
            korisnik.setIme(registracijaDto.getIme());
        }

        if(registracijaDto.getPrezime() != null){
            korisnik.setPrezime(registracijaDto.getPrezime());
        }

        if(registracijaDto.getPol() != null){
            korisnik.setPol(registracijaDto.getPol());
        }

        if(registracijaDto.getDatumRodjenja() != null) {
            korisnik.setDatumRodjenja(registracijaDto.getDatumRodjenja());
        }

        korisnikRepository.save(korisnik);

        return korisnik;
    }
}
