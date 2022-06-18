package com.example.veb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @Column
    private Integer broj_sakupljenih_bodova;

    @ManyToOne
    private TipKupca tip_kupca;

    public Kupac() {
        this.uloga = Uloga.KUPAC;
    }

    public Kupac(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum_rodjenja) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum_rodjenja);
        this.uloga = Uloga.KUPAC;
    }



    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Integer getBroj_sakupljenih_bodova() {
        return broj_sakupljenih_bodova;
    }

    public void setBroj_sakupljenih_bodova(Integer broj_sakupljenih_bodova) {
        this.broj_sakupljenih_bodova = broj_sakupljenih_bodova;
    }

    public TipKupca getTip_kupca() {
        return tip_kupca;
    }

    public void setTip_kupca(TipKupca tip_kupca) {
        this.tip_kupca = tip_kupca;
    }
}
