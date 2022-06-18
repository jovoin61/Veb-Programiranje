package com.example.veb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.*;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne
    protected Restoran restoran;

    public Menadzer() {
        this.uloga = Uloga.MENADZER;
    }

    public Menadzer(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum_rodjenja) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum_rodjenja);
        this.uloga = Uloga.MENADZER;
    }



    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
