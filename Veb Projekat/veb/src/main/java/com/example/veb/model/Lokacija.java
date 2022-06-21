package com.example.veb.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double geografskaDuzina;

    @Column
    private Double geografskaSirina;

    @Column
    private String adresa;

    public Lokacija() {
    }

    public Lokacija(Double geografskaDuzina, Double geografskaSirina, String adresa) {
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.adresa = adresa;
    }

    public Double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(Double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public Double getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(Double geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
