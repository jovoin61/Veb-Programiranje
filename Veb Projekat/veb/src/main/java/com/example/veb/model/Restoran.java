package com.example.veb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Restoran implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id_restorana;

    @Column
    protected String naziv ;

    @Column
    protected String tip;

    @OneToMany
    protected Set<Artikal> artikli = new HashSet<>();

    @OneToOne
    protected Lokacija lokacija;

    public Restoran() {
    }

    public Restoran(String naziv, String tip, Lokacija lokacija) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija = lokacija;
    }

    public Long getId_restorana() {
        return id_restorana;
    }

    public void setId_restorana(Long id_restorana) {
        this.id_restorana = id_restorana;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
