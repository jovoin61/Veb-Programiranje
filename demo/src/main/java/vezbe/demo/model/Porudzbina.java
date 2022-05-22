package vezbe.demo.model;

import java.util.HashSet;
import java.util.Set;

public class Porudzbina {

    enum Status{
        OBRADA,
        U_PRIPREMI,
        CEKA_DOSTAVLJACA,
        U_TRANSPORTU,
        DOSTAVLJENA,
        OTKAZANA
    }
    private Integer UUID;
    private Set<Artikal> artikli = new HashSet<>();
    private Restoran restoran ;
    // todo vreme
    private Double cena;
    private Kupac kupac;
    private Status status;




}
