package com.example.veb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.*;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    public Menadzer() {
        this.uloga = Uloga.MENADZER;
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.uloga = Uloga.MENADZER;
    }

    public Menadzer(Korisnik korisnik) {
        this.korisnickoIme = korisnik.korisnickoIme;
        this.lozinka = korisnik.lozinka;
        this.ime = korisnik.ime;
        this.prezime = korisnik.prezime;
        this.pol = korisnik.pol;
        this.datumRodjenja = korisnik.datumRodjenja;

        this.uloga = Uloga.MENADZER;

        this.restoran = null;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }


}
