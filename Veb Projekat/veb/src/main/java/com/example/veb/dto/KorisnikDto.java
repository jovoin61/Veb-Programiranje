package com.example.veb.dto;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Pol;
import com.example.veb.model.Uloga;

import javax.persistence.*;
import java.util.Date;

public class KorisnikDto {

    protected Long id;

    protected String korisnickoIme;

    protected String ime;

    protected String prezime;

    protected Pol pol;

    protected Date datumRodjenja;

    protected Uloga uloga;

    public KorisnikDto(){}

    public KorisnikDto(Korisnik korisnik){
        this.id = korisnik.getId();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.pol = korisnik.getPol();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.uloga = korisnik.getUloga();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
}
