package com.example.veb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.*;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dostavljac")
    @JsonIgnore
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac() {
        this.uloga = Uloga.DOSTAVLJAC;
    }

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.uloga = Uloga.DOSTAVLJAC;
    }

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja, Set<Porudzbina> porudzbine) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.porudzbine = porudzbine;
        this.uloga = Uloga.DOSTAVLJAC;
    }

    public Dostavljac(Korisnik korisnik) {
        this.korisnickoIme = korisnik.korisnickoIme;
        this.lozinka = korisnik.lozinka;
        this.ime = korisnik.ime;
        this.prezime = korisnik.prezime;
        this.pol = korisnik.pol;
        this.datumRodjenja = korisnik.datumRodjenja;

        this.uloga = Uloga.DOSTAVLJAC;

        porudzbine = null;

    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }


}
