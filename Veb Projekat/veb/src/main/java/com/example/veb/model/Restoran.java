package com.example.veb.model;

import com.example.veb.dto.RestoranDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Restoran implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv ;

    @Column
    private String tip;

    @OneToMany//(fetch = FetchType.EAGER, mappedBy = "restoran")
    private Set<Artikal> artikli = new HashSet<>();


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lokacija_id")
    private Lokacija lokacija;


    public Restoran() {
    }

    public Restoran(String naziv, String tip, Lokacija lokacija) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija = lokacija;
    }

    public Restoran (RestoranDto restoranDto){
        this.naziv = restoranDto.getNaziv();
        this.tip = restoranDto.getTip();
        //this.lokacija = restoranDto.getLokacija();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
