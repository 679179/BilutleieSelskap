import java.util.List;

public class AdminApp {
	
    private static List<Utleiekontor> utleiekontorer;
    private static List<Kunde> kunder;
	
	
	public static void start(List<Utleiekontor> utleiekontorerRef, List<Kunde> kunderRef) {
        utleiekontorer = utleiekontorerRef;
        kunder = kunderRef;
		
	}

}
