package com.example.veb.dto;


import com.example.veb.model.Kpgs;
import com.example.veb.model.Porudzbina;
import com.example.veb.model.Status;

import java.util.*;

public class PorudzbinaDto{

        private Long uuid;
        private Set<Kpgs> stavke = new HashSet<>();
        private RestoranDto restoran;
        private Date vremePorudzbine;
        private Double cena;
        private Status status;

        public PorudzbinaDto() {
        }

        public PorudzbinaDto(Porudzbina p) {
                this.uuid = p.getUuid();
                this.stavke = p.getStavke();
                this.restoran = new RestoranDto(p.getRestoran());
                this.vremePorudzbine = p.getVremePorudzbine();
                this.cena = p.getCena();
                this.status = p.getStatus();
        }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Set<Kpgs> getStavke() {
                return stavke;
        }

        public void setStavke(Set<Kpgs> stavke) {
                this.stavke = stavke;
        }

        public RestoranDto getRestoran() {
                return restoran;
        }

        public void setRestoran(RestoranDto restoran) {
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

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
                this.status = status;
        }
}
