package com.example.veb.configuration;

import com.example.veb.model.*;


import com.example.veb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DatabaseConfiguration {

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
        Korisnik ranbo = new Korisnik("ranbo", "fap", "antonije", "pusic", Pol.MUSKI, kalendar.getTime());
        ranbo.setUloga(Uloga.ADMIN);

        Kupac testKupac = new Kupac("kupac", "kupac", "Test", "Kupac", Pol.ZENSKI, kalendar.getTime());

        korisnikRepository.save(ranbo);
        kupacRepository.save(testKupac);
        Kupac testKupac1 = new Kupac("asd", "asd", "Test", "Kupac", Pol.ZENSKI, kalendar.getTime());
        kupacRepository.save(testKupac1);

        kalendar.set(1999, Calendar.MARCH,24);
        Menadzer dzemo = new Menadzer("dzemo", "dzemo", "dzemo", "dzemic", Pol.MUSKI, kalendar.getTime());
        menadzerRepository.save(dzemo);

        Menadzer jovo = new Menadzer("jovo", "car", "dzemo", "dzemic", Pol.MUSKI, kalendar.getTime());
        menadzerRepository.save(jovo);

        Korisnik korisnik1 = new Korisnik("korisnik1", "korisnik123", "Korisnik1", "jedan", Pol.MUSKI, kalendar.getTime() );
        korisnikRepository.save(korisnik1);

        Korisnik korisnik2 = new Korisnik("korisnik2", "korisnik123", "Korisnik2", "dva", Pol.MUSKI, kalendar.getTime() );
        korisnikRepository.save(korisnik2);


        Artikal menjac = new Artikal("Menjac" ,16.20, Tip.JELO,300,"Menjac od fapa");

        Artikal volan = new Artikal("Volan" ,16.16, Tip.JELO,130,"Volan od fapa");
        Artikal felna = new Artikal("Felna" ,13.14, Tip.PICE,80,"Felna od fapa");



        Lokacija priboj = new Lokacija(45.00, 45.00, "priboj");
        Set<Artikal> artikli = new HashSet<>();

        lokacijaRepository.save(priboj);

        Lokacija sase = new Lokacija(44.0, 45.0, "Sase");
        lokacijaRepository.save(sase);


        Lokacija indjija = new Lokacija(44.0, 45.0, "Indjija");
        lokacijaRepository.save(indjija);

        Restoran fap = new Restoran("F.A.P.", "Kamionski", priboj);
        Restoran aa = new Restoran("Vrlo", "Kafana", priboj);
        Restoran jara = new Restoran("Kod Jare", "Kafana", sase);
        Restoran velja = new Restoran("Kod Velje", "Pekara", sase);
        Restoran dobra = new Restoran("Skroz Dobra", "Pekara", indjija);
        Restoran stanko = new Restoran("Stanisic", "Pekara", sase);
        fap.setStatusRestorana(StatusRestorana.RADI);
        aa.setStatusRestorana(StatusRestorana.NE_RADI);


        fap.setArtikli(Set.of(menjac,volan));
        aa.setArtikli(Set.of(felna));
        //asdf
        Kpgs s1 = new Kpgs(menjac, 3);
        Kpgs s2 = new Kpgs(menjac, 1);
        Kpgs s3 = new Kpgs(volan, 2);
        Kpgs s4 = new Kpgs(felna, 3);
        Kpgs s5 = new Kpgs(felna, 2);


        Dostavljac brko = new Dostavljac("brko123", "brko123", "Brko", "Brkic", Pol.MUSKI, kalendar.getTime());

        Dostavljac micko = new Dostavljac("mica", "mica", "Micko", "Mickovic", Pol.MUSKI, kalendar.getTime());
        dostavljacRepository.save(micko);

        kalendar.set(2021, Calendar.MAY,26);
        Porudzbina p1 = new Porudzbina( fap , kalendar.getTime() , testKupac , Status.DOSTAVLJENA);
        Porudzbina p2 = new Porudzbina( aa , kalendar.getTime() , testKupac , Status.U_TRANSPORTU);
        p1.setStavke(Set.of(s1,s3,s4));
        p2.setStavke(Set.of(s2,s5));
       // porudzbinaRepository.saveAll(List.of(p1,p2));
       // kpgsRepository.saveAll(List.of(s1,s2,s3,s4,s5));
       // porudzbinaRepository.saveAll(List.of(p1,p2));
        System.out.println(s1.getPorudzbina());
        System.out.println(p1.getStavke());
        s1.setPorudzbina(p1);
        s2.setPorudzbina(p2);
        s3.setPorudzbina(p1);
        s4.setPorudzbina(p1);
        s5.setPorudzbina(p2);
        s1.setKupac(testKupac);
        s2.setKupac(testKupac);
        s3.setKupac(testKupac);
        s4.setKupac(testKupac);
        s5.setKupac(testKupac);
        s1.setRestoran(s1.getArtikal().getRestoran());
        s2.setRestoran(s2.getArtikal().getRestoran());
        s3.setRestoran(s3.getArtikal().getRestoran());
        s4.setRestoran(s4.getArtikal().getRestoran());
        s5.setRestoran(s5.getArtikal().getRestoran());

        fap.setPorudzbina(List.of(p1));
        aa.setPorudzbina(List.of(p2));
        //restoranRepository.save(fap);
        //restoranRepository.save(aa);
        artikalRepository.saveAll(List.of(volan, felna, menjac));
        restoranRepository.saveAll(List.of(fap, aa, jara, velja, dobra, stanko));

        dzemo.setRestoran(fap);
        jovo.setRestoran(aa);


        menadzerRepository.save(dzemo);
        menadzerRepository.save(jovo);
       // porudzbinaRepository.saveAll(List.of(p1,p2));
       // kpgsRepository.saveAll(List.of(s1,s2,s3,s4,s5));


        //kpgsRepository.saveAll(List.of(s1,s2,s3,s4,s5));
        //porudzbinaRepository.saveAll(List.of(p1,p2));
        //kpgsRepository.saveAll(List.of(s1,s2,s3,s4,s5));
        brko.setPorudzbine(Set.of(p2));
        dostavljacRepository.save(brko);



        System.out.println(p1.getStavke());
        System.out.println(p2.getStavke());
        System.out.println(testKupac.getPorudzbine());
        testKupac.setPorudzbine(Set.of(p1,p2));
        System.out.println(testKupac.getPorudzbine());
        kupacRepository.save(testKupac);
       // dostavljacRepository.save(brko);

        /*Set<Porudzbina> asdf = new HashSet<>();
        asdf.add(p1);
        asdf.add(p2);
        testKupac.setPorudzbine(asdf);*/

        TipKupca tip1 = new TipKupca("zlatni",20,10);
        testKupac.setTipKupca(tip1);


        tipKupcaRepository.save(tip1);

        Komentar k1 = new Komentar(testKupac, fap,"dobar", 9);

        komentarRepository.save(k1);

        return true;
    }
}
