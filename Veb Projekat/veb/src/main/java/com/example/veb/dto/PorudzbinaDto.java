package com.example.veb.dto;


import com.example.veb.model.Kpgs;
import com.example.veb.model.Porudzbina;
import com.example.veb.model.Status;

import java.util.*;

public class PorudzbinaDto{

        private UUID uuid;
        private Set<Kpgs> stavke = new HashSet<>();
        private RestoranDto restoran;
        private Date vremePorudzbine;
        private Double cena;
       //private KorisnikDto kupac; //KupacDto
        private Status status;

        public PorudzbinaDto() {
        }

        public PorudzbinaDto(Porudzbina p) {
                this.uuid = p.getUuid();
                this.stavke = p.getStavke();
               // setStavke(p.getStavke());
                this.restoran = new RestoranDto(p.getRestoran());
                this.vremePorudzbine = p.getVremePorudzbine();
                this.cena = p.getCena();
                //this.kupac = new KorisnikDto(p.getKupac());
                this.status = p.getStatus();
        }

        public UUID getUuid() {
                return uuid;
        }

        public void setUuid(UUID uuid) {
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

        /*public KorisnikDto getKupac() {
                return kupac;
        }

        public void setKupac(KorisnikDto kupac) {
                this.kupac = kupac;
        }*/

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
                this.status = status;
        }
}
