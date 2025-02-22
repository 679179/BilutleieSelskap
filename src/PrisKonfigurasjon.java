import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PrisKonfigurasjon {

    private static final Map<BilKategori, Integer> dagspriser = new HashMap<>();
    private static final Map<Set<Lokasjon>, Integer> gebyrer = new HashMap<>();

    static {
        dagspriser.put(BilKategori.LITEN, 1000);
        dagspriser.put(BilKategori.MELLOMSTOR, 2000);
        dagspriser.put(BilKategori.STOR, 3000);
        dagspriser.put(BilKategori.STASJONSVOGN, 4000);
        
        gebyrer.put(createLokasjonPair(Lokasjon.FORDE, Lokasjon.OSLO), 2000);
        gebyrer.put(createLokasjonPair(Lokasjon.FORDE, Lokasjon.BERGEN), 1000);
        gebyrer.put(createLokasjonPair(Lokasjon.FORDE, Lokasjon.TRONDHEIM), 1500);
        gebyrer.put(createLokasjonPair(Lokasjon.FORDE, Lokasjon.KRISTIANSAND), 1800);

        gebyrer.put(createLokasjonPair(Lokasjon.OSLO, Lokasjon.BERGEN), 2200);
        gebyrer.put(createLokasjonPair(Lokasjon.OSLO, Lokasjon.TRONDHEIM), 2500);
        gebyrer.put(createLokasjonPair(Lokasjon.OSLO, Lokasjon.KRISTIANSAND), 1800);

        gebyrer.put(createLokasjonPair(Lokasjon.BERGEN, Lokasjon.TRONDHEIM), 1500);
        gebyrer.put(createLokasjonPair(Lokasjon.BERGEN, Lokasjon.KRISTIANSAND), 1700);

        gebyrer.put(createLokasjonPair(Lokasjon.TRONDHEIM, Lokasjon.KRISTIANSAND), 2600);
    }

    private static Set<Lokasjon> createLokasjonPair(Lokasjon lok1, Lokasjon lok2) {
        Set<Lokasjon> lokasjonPair = new TreeSet<>();
        lokasjonPair.add(lok1);
        lokasjonPair.add(lok2);
        return lokasjonPair;
    }

    public static int getDagspris(BilKategori kategori) {
        return dagspriser.getOrDefault(kategori, 0); 
    }

    public static int getGebyr(Lokasjon lok1, Lokasjon lok2) {
        Set<Lokasjon> lokasjonPair = createLokasjonPair(lok1, lok2);
        return gebyrer.getOrDefault(lokasjonPair, 0); 
    }
}
