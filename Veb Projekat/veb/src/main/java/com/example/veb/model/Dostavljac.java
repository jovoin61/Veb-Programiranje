package com.example.veb.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.*;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany
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

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }
}
