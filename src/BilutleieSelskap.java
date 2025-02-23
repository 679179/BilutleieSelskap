import static javax.swing.JOptionPane.showInputDialog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Random;
import java.time.temporal.ChronoUnit;

public class BilutleieSelskap {

	private static String navn;
	private static Adresse firmaAdresse;
	private static String tlfNmr;
	private static List<Utleiekontor> utleiekontorer;
	private static List<Kunde> kunder;
	
	
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
        
        switch (selectedRole) {
            case KUNDE:
                BilReservasjonApp.start(utleiekontorer, kunder);
                break;
            case KONSULENT:
                KonsulentApp.start(utleiekontorer, kunder);
                break;
            case ADMIN:
                AdminApp.start(utleiekontorer, kunder);
                break;
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }
    }
    
	
	/*

	
	public static void konsulentProgram() {
		String velgFunskjon = showInputDialog("Velg funskjon | 1 for å leige bil | 2 for å returnere bil");
		switch(velgFunskjon) {
		case "1":
			searchAndReserveCar();
			break;
		case "2":
			String regNmr = showInputDialog("Skriv inn registreringsnummer på bilen du skal returnere");
			returnereBil(regNmr);
			break;
		default:
			System.out.println("Du må velge 1 for å leige bil og 2 for å returnere bil");
		}
	}
	
	public static void returnereBil(String regNmr) {
		List<Bil> bil = getAllCars().stream().filter(x -> x.getRegistreringsNmr().equals(regNmr) && x.getLedig() == false).toList();
		Bil funnetBil = bil.get(0);
		funnetBil.setLedig(true);
		LocalDateTime dato = LocalDateTime.now();
		// TODO  når bil leveres --> må levers til utleiekontor, utleiekontor i listen må ha liste over biler 
		
		String kilometer = showInputDialog("Skriv inn kilometerstand på bilen");
		Integer.parseInt(kilometer);
		JOptionPane.showMessageDialog(null, "Bil med registreringsnummer " + regNmr + " har blitt returnert med " 
		+ kilometer + " kilometerstand. Returnert: " + dato);
	}
	
	public static void adminProgram() {
		String velgFunskjon = showInputDialog("Velg funskjon | 1 for å opprette kontor | 2 for å legge til biler");
		
		switch(velgFunskjon) {
		case "1":
			addNewKontor();
			break;
		case "2":
			addNewCar();
			break;
		}
	}

public static void addNewCar() {
    // HENTER KUNDE INFO 

    JPanel panel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
    panel.setLayout(boxLayout);

    // Create the input fields
    JTextField regNr = new JTextField(20);
    JTextField modell = new JTextField(20);
    JTextField farge = new JTextField(20);
    JTextField gruppe = new JTextField(20);
    JTextField ledig = new JTextField(20);
    JTextField utleiekontor = new JTextField(20);

    // Add labels and input fields to the panel
    panel.add(new JLabel("Registreringsnummer"));
    panel.add(regNr);
    panel.add(new JLabel("Bilmodell"));
    panel.add(modell);
    panel.add(new JLabel("Farge"));
    panel.add(farge);
    panel.add(new JLabel("Bil gruppe"));
    panel.add(gruppe);
    panel.add(new JLabel("Ledig"));
    panel.add(ledig);
    panel.add(new JLabel("Utleiekontor"));
    panel.add(utleiekontor);

    // Show the dialog with the panel
    int option = JOptionPane.showConfirmDialog(null, panel, "Enter Car Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    // If OK button was pressed, collect the data
    List<String> carData = new ArrayList<>();
    if (option == JOptionPane.OK_OPTION) {
        // Collect input from the fields into a list
    	carData.add(regNr.getText());
    	carData.add(modell.getText());
    	carData.add(farge.getText());
    	carData.add(gruppe.getText());
    	carData.add(ledig.getText());
    	carData.add(utleiekontor.getText());

        // Output the collected data (or process it as needed)
        System.out.println(carData);
    }

}

public static void addNewKontor() {
    // HENTER KUNDE INFO 

    JPanel panel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
    panel.setLayout(boxLayout);

    // Create the input fields
    JTextField kontorNmr = new JTextField(20);
    JTextField gateadresse = new JTextField(20);
    JTextField postNmr = new JTextField(20);
    JTextField poststed = new JTextField(20);
    JTextField tlfNmr = new JTextField(20);

    // Add labels and input fields to the panel
    panel.add(new JLabel("Kontornummer"));
    panel.add(kontorNmr);
    panel.add(new JLabel("Gateadresse"));
    panel.add(gateadresse);
    panel.add(new JLabel("Postnummer"));
    panel.add(postNmr);
    panel.add(new JLabel("Poststed"));
    panel.add(poststed);
    panel.add(new JLabel("Telefon nummer"));
    panel.add(tlfNmr);

    // Show the dialog with the panel
    int option = JOptionPane.showConfirmDialog(null, panel, "Enter Car Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    // If OK button was pressed, collect the data
    List<String> kontorData = new ArrayList<>();
    if (option == JOptionPane.OK_OPTION) {
        // Collect input from the fields into a list
    	kontorData.add(kontorNmr.getText());
    	kontorData.add(gateadresse.getText());
    	kontorData.add(postNmr.getText());
    	kontorData.add(poststed.getText());
    	kontorData.add(tlfNmr.getText());

    	Adresse adresse1 = new Adresse(gateadresse.toString(), postNmr.toString(), poststed.toString());
    	Utleiekontor kontor1 = new Utleiekontor(Integer.parseInt(kontorNmr.toString()), adresse1, tlfNmr.toString());
    	utleiekontorer.add(kontor1);
    	
        // Output the collected data (or process it as needed)
        System.out.println(kontorData);
    }
    
}
	
	public static LocalDateTime getLocalTime() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");
		LocalDateTime tidspunkt = LocalDateTime.now();
		
		return tidspunkt;
	}	
	
	*/
	
}
