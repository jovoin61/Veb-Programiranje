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

    public Dostavljac(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum_rodjenja) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum_rodjenja);
        this.uloga = Uloga.DOSTAVLJAC;
    }

    public Dostavljac(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum_rodjenja, Set<Porudzbina> porudzbine) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum_rodjenja);
        this.porudzbine = porudzbine;
        this.uloga = Uloga.DOSTAVLJAC;
    }

    public Dostavljac(Korisnik korisnik) {
        this.korisnickoIme = korisnik.korisnickoIme;
        this.lozinka = korisnik.lozinka;
        this.ime = korisnik.ime;
        this.prezime = korisnik.prezime;
        this.pol = korisnik.pol;
        this.datum_rodjenja = korisnik.datum_rodjenja;

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
