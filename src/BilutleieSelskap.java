import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BilutleieSelskap {

	private static String navn;
	private static Adresse firmaAdresse;
	private static String tlfNmr;
	private static List<Utleiekontor> utleiekontorer;
	private static List<Kunde> kunder;
	public static List<String> lokasjoner;
	
	
	static {
		navn = "Verdens beste bilutleieselskap";
		firmaAdresse = new Adresse("Skaratunet 13", "6018", "Forde");
		tlfNmr = "12345678";
		utleiekontorer = new ArrayList<Utleiekontor>();
		kunder = new ArrayList<Kunde>();		
	}
	
	
	public static void main(String[] args) {
		generateDummyData();
		startApp();		
	}
	
	
	public static void generateDummyData() {
		DummyDataGenerator.addGeneratedKontorer(utleiekontorer);
		DummyDataGenerator.addGeneratedCarsToKontorer(utleiekontorer);
		DummyDataGenerator.addGeneratedReservations(utleiekontorer, kunder);
		lokasjoner = utleiekontorer.stream().map(l -> l.getLokasjon()).toList();
	}

	public static void startApp() {
        BrukerRolle[] options = { BrukerRolle.KUNDE, BrukerRolle.KONSULENT, BrukerRolle.ADMIN };
        BrukerRolle selectedRole = (BrukerRolle) JOptionPane.showInputDialog(
                null,
                "Velg brukersystem",
                "Bruker Valg",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        if(selectedRole == null) return;
        switch (selectedRole) {
            case KUNDE:
                BilReservasjonApp.start();
                break;
            case KONSULENT:
                KonsulentApp.start();
                break;
            case ADMIN:
                AdminApp.start();
                break;
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }
    }
	
	public static List<String> getLokasjoner() {
        return lokasjoner;
    }
	
    public static List<Utleiekontor> getUtleiekontorer() {
        return utleiekontorer;
    }

    public static List<Kunde> getKunder() {
        return kunder;
    }
    
    public static void addNewUtleiekontor(Utleiekontor kontor) {
    	utleiekontorer.add(kontor);
    }
	
}
