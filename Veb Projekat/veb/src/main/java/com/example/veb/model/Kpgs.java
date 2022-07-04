package com.example.veb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Kpgs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "artikal_id")
    private Artikal artikal;

    @Column
    private Double ukupnaCena;

    @Column
    private Integer brojArtikala;

    /*@ManyToOne//(cascade = CascadeType.ALL)//(fetch = FetchType.EAGER)
    //@JoinColumn(name = "porudzbina_id")
    @JsonIgnore
    private Porudzbina porudzbina;*/
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Porudzbina porudzbina;

    @OneToOne
    @JsonIgnore
    private Kupac kupac;

    @OneToOne
    @JsonIgnore
    private Restoran restoran;

    public Kpgs() {
        super();
    }

    public Kpgs(Porudzbina porudzbina1,Artikal artikal, Integer brojArtikala,Double ukupnaCena) {
        this.porudzbina = porudzbina1;
        this.artikal = artikal;
        this.brojArtikala = brojArtikala;
        this.ukupnaCena = ukupnaCena;
    }
    public Kpgs(Artikal artikal, Integer brojArtikala) {
        this.artikal = artikal;
        this.brojArtikala = brojArtikala;
        this.ukupnaCena = artikal.getCena() * brojArtikala;
    }

    public Kpgs(Artikal artikal, Double ukupnaCena, Integer brojArtikala, Kupac kupac , Restoran restoran) {
        this.artikal = artikal;
        this.ukupnaCena = ukupnaCena;
        this.brojArtikala = brojArtikala;
        this.kupac = kupac;
        this.restoran = restoran;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(Double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Integer getBrojArtikala() {
        return brojArtikala;
    }

    public void setBrojArtikala(Integer brojArtikala) {
        this.brojArtikala = brojArtikala;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
