package com.example.veb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kupac")
    //@JsonIgnore
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @Column
    private Integer brojSakupljenihBodova;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kupac_id")
    private TipKupca tipKupca;

    public Kupac() {
        this.uloga = Uloga.KUPAC;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.uloga = Uloga.KUPAC;
    }



    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Integer getBrojSakupljenihBodova() {
        return brojSakupljenihBodova;
    }

    public void setBrojSakupljenihBodova(Integer brojSakupljenihBodova) {
        this.brojSakupljenihBodova = brojSakupljenihBodova;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }


}
