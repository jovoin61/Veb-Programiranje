package com.example.veb.model;




import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stavkeId")
    protected Set<Kpgs> stavke;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restoran_id")
    protected Restoran restoran;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    protected Date vreme_porudzbine;

    @Column
    protected Double cena;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kupac_id")
    protected Kupac kupac;

    @Enumerated(EnumType.STRING)
    @Column
    protected Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "dostavljac_id")
    protected Dostavljac dostavljac;

    public Porudzbina() {
    }

    public Porudzbina(@NotNull Set<Kpgs> stavke, Restoran restoran, Date vreme_porudzbine, Kupac kupac, Status status) {
        this.stavke = stavke;
        this.restoran = restoran;
        this.vreme_porudzbine = vreme_porudzbine;
       /*
        for(Kpgs x : stavke ) {
            this.cena += x.getUkupna_cena();
        }
        */
        this.kupac = kupac;
        this.status = status;
    }



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

    public Date getVreme_porudzbine() {
        return vreme_porudzbine;
    }

    public void setVreme_porudzbine(Date vreme_porudzbine) {
        this.vreme_porudzbine = vreme_porudzbine;
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
}