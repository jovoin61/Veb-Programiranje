package com.example.veb.configuration;

import com.example.veb.model.*;


import com.example.veb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DatabaseConfiguration {  //nemam knjizicu

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KpgsRepository kpgsRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Bean
    public boolean Instantiate() {

        Calendar kalendar = new GregorianCalendar();

        kalendar.set(1963, Calendar.JUNE,14);
        Korisnik ranbo = new Kupac("baustelac_ranbo", "fap", "antonije", "pusic", Pol.MUSKI, kalendar.getTime());
        ranbo.setUloga(Uloga.ADMIN);

        Kupac kupac1 = new Kupac("kupac123", "kupac123", "Kup", "Ac", Pol.ZENSKI, kalendar.getTime());

        korisnikRepository.save(ranbo);
        kupacRepository.save(kupac1);

        kalendar.set(1953, Calendar.JANUARY,19);
        Dostavljac brko = new Dostavljac("brko123", "brko123", "Brko", "Brkic", Pol.MUSKI, kalendar.getTime());

        dostavljacRepository.save(brko);

        kalendar.set(1999, Calendar.MARCH,24);
        Menadzer dzemo = new Menadzer("zastavnik-dzemo", "celicnakrila", "dzemo", "dzemic", Pol.MUSKI, kalendar.getTime());

        menadzerRepository.save(dzemo);



        Artikal menjac = new Artikal("Menjac" ,16.20, Tip.JELO,300,"Menjac od fapa");

        Artikal volan = new Artikal("Volan" ,16.16, Tip.JELO,130,"Volan od fapa");
        Artikal felna = new Artikal("Felna" ,13.14, Tip.PICE,80,"Felna od fapa");



        Lokacija priboj = new Lokacija(45.00, 45.00, "priboj");
        Set<Artikal> artikli = new HashSet<>();

        lokacijaRepository.save(priboj);

        artikli.add(menjac);
        artikli.add(volan);
        artikli.add(felna);

        artikalRepository.saveAll(List.of(volan, felna, menjac));

        Restoran r = new Restoran();

        Restoran fap = new Restoran("F.A.P.", "kamionski", priboj);

        restoranRepository.saveAll(List.of(fap, r));

        dzemo.setRestoran(fap);
        fap.setArtikli(artikli);

        korisnikRepository.save(dzemo);

        Kpgs s1 = new Kpgs(menjac, 3);
        Kpgs s2 = new Kpgs(menjac, 1);
        Kpgs s3 = new Kpgs(volan, 2);
        Kpgs s4 = new Kpgs(felna, 3);
        Kpgs s5 = new Kpgs(felna, 2);



        Set<Kpgs> stavke1 = new HashSet<>();
        Set<Kpgs> stavke2 = new HashSet<>();

        stavke1.add(s1);
        stavke1.add(s3);
        stavke1.add(s4);

        stavke2.add(s2);
        stavke2.add(s5);
        kpgsRepository.saveAll(List.of(s1,s2,s3,s4,s5));

        kalendar.set(2021, Calendar.MAY,26);
        Porudzbina p1 = new Porudzbina(stavke1, fap , kalendar.getTime() , kupac1 , Status.CEKA_DOSTAVLJACA);
        //porudzbinaRepository.saveAndFlush(p1);
        Porudzbina p2 = new Porudzbina(stavke2, fap , kalendar.getTime() , kupac1 , Status.CEKA_DOSTAVLJACA);
        //porudzbinaRepository.save(p2);
        porudzbinaRepository.saveAll(List.of(p1,p2));



        TipKupca tip1 = new TipKupca("zlatni",20,10);
        kupac1.setTip_kupca(tip1);


        tipKupcaRepository.save(tip1);

        Komentar k1 = new Komentar(kupac1, fap,"dobar", 9);

        komentarRepository.save(k1);

        return true;
    }
}
