package com.example.veb.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue
    private UUID uuid;

    @OneToMany//(cascade = CascadeType.ALL)//(fetch = FetchType.EAGER, mappedBy = "porudzbina")
    private Set<Kpgs> stavke = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date vremePorudzbine;

    @Column
    private Double cena = 0.00;

    @ManyToOne
    @JsonIgnore
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dostavljac_id")
    private Dostavljac dostavljac;*/

    public Porudzbina() {
    }

    public Porudzbina(Restoran restoran,  Date vremePorudzbine, Kupac kupac ,Status status) {
        this.restoran = restoran;
        this.vremePorudzbine = vremePorudzbine;
        this.kupac = kupac;
        this.status = status;
    }



    /*public Porudzbina(@NotNull Set<Kpgs> stavke, Restoran restoran, Date vremePorudzbine, Kupac kupac, Status status,Dostavljac dostavljac) {
        this.stavke = stavke;
        this.restoran = restoran;
        this.vremePorudzbine = vremePorudzbine;
        this.kupac = kupac;
        this.status = status;
        for (Kpgs k : stavke){
            this.cena  += k.getUkupnaCena();
        }
        this.dostavljac=dostavljac;
    }*/



    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<Kpgs> getArtikli() {
        return stavke;
    }

    public void setArtikli(Set<Kpgs> stavke) {
        this.stavke = stavke;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getVremePorudzbine() {
        return vremePorudzbine;
    }

    public void setVremePorudzbine(Date vremePorudzbine) {
        this.vremePorudzbine = vremePorudzbine;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Kpgs> getStavke() {
        return stavke;
    }

    public void setStavke(Set<Kpgs> stavke) {
        this.stavke = stavke;
    }

    /*public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }*/


}
