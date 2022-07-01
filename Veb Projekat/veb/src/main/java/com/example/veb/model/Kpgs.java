package com.example.veb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Kpgs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artikal_id")
    private Artikal artikal;

    @Column
    private Double ukupnaCena;

    @Column
    private Integer brojArtikala;

    /*@ManyToOne//(cascade = CascadeType.ALL)//(fetch = FetchType.EAGER)
    //@JoinColumn(name = "porudzbina_id")
    @JsonIgnore
    private Porudzbina porudzbina;*/
    @ManyToOne
    @JsonIgnore
    private Porudzbina porudzbina;

    public Kpgs() {
    }

    public Kpgs(Porudzbina porudzbina1,Artikal artikal, Integer brojArtikala) {
        this.porudzbina = porudzbina1;
        this.artikal = artikal;
        this.brojArtikala = brojArtikala;
        this.ukupnaCena = artikal.getCena() * brojArtikala;
    }
    public Kpgs(Artikal artikal, Integer brojArtikala) {
        this.artikal = artikal;
        this.brojArtikala = brojArtikala;
        this.ukupnaCena = artikal.getCena() * brojArtikala;
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

    /*public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }*/


}
