import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PrisKonfigurasjon {

    private static final Map<BilKategori, Integer> dagspriser = new HashMap<>();
    private static final Map<Set<String>, Integer> gebyrer = new HashMap<>();

    static {
        dagspriser.put(BilKategori.LITEN, 1000);
        dagspriser.put(BilKategori.MELLOMSTOR, 2000);
        dagspriser.put(BilKategori.STOR, 3000);
        dagspriser.put(BilKategori.STASJONSVOGN, 4000);
        
        // Now using Strings instead of Lokasjon enum values
        gebyrer.put(createLokasjonPair("FORDE", "OSLO"), 2000);
        gebyrer.put(createLokasjonPair("FORDE", "BERGEN"), 1000);
        gebyrer.put(createLokasjonPair("FORDE", "TRONDHEIM"), 1500);
        gebyrer.put(createLokasjonPair("FORDE", "KRISTIANSAND"), 1800);

        gebyrer.put(createLokasjonPair("OSLO", "BERGEN"), 2200);
        gebyrer.put(createLokasjonPair("OSLO", "TRONDHEIM"), 2500);
        gebyrer.put(createLokasjonPair("OSLO", "KRISTIANSAND"), 1800);

        gebyrer.put(createLokasjonPair("BERGEN", "TRONDHEIM"), 1500);
        gebyrer.put(createLokasjonPair("BERGEN", "KRISTIANSAND"), 1700);

        gebyrer.put(createLokasjonPair("TRONDHEIM", "KRISTIANSAND"), 2600);
    }

    private static Set<String> createLokasjonPair(String lok1, String lok2) {
        Set<String> lokasjonPair = new TreeSet<>();
        lokasjonPair.add(lok1);
        lokasjonPair.add(lok2);
        return lokasjonPair;
    }

    public static int getDagspris(BilKategori kategori) {
        return dagspriser.getOrDefault(kategori, 0); 
    }

    public static int getGebyr(String lok1, String lok2) {
        Set<String> lokasjonPair = createLokasjonPair(lok1, lok2);
        return gebyrer.getOrDefault(lokasjonPair, 0); 
    }
}

