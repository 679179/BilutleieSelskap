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
	
	
	public static void main(String[] args) {
		utleiekontorer = new ArrayList<Utleiekontor>();
		kunder = new ArrayList<Kunde>();
		addGeneratedKontorer();
		addGeneratedCarsToKontorer();
		addGeneratedReservations();
		
		for(Utleiekontor kontor: utleiekontorer) {
			System.out.println(kontor);
		}
		// startApp();		
	}
	
	public static void addGeneratedKontorer() {
	    utleiekontorer.add(new Utleiekontor(Lokasjon.FORDE, 1, new Adresse("Hafstadvegen 23", "6800", "Førde"), "12345678"));
	    utleiekontorer.add(new Utleiekontor(Lokasjon.OSLO, 2, new Adresse("Karl Johans gate 15", "0154", "Oslo"), "23456789"));
	    utleiekontorer.add(new Utleiekontor(Lokasjon.TRONDHEIM, 3, new Adresse("Munkegata 34", "7011", "Trondheim"), "34567890"));
	    utleiekontorer.add(new Utleiekontor(Lokasjon.BERGEN, 4, new Adresse("Bryggen 47", "5003", "Bergen"), "45678901"));
	    utleiekontorer.add(new Utleiekontor(Lokasjon.KRISTIANSAND, 5, new Adresse("Markens gate 35", "4612", "Kristiansand"), "56789012"));
	}
	
	public static void addGeneratedCarsToKontorer() {
	    Bil[] biler = {
	            new Bil("AB1234", null, "Toyota", "Corolla", Color.RED, BilKategori.LITEN, false, 50000),
	            new Bil("CD5678", null, "BMW", "i3", Color.BLUE, BilKategori.MELLOMSTOR, false, 75000),
	            new Bil("EF9012", null, "Tesla", "Model S", Color.BLACK, BilKategori.STASJONSVOGN, false, 20000),
	            new Bil("GH3456", null, "Volkswagen", "Golf", Color.SILVER, BilKategori.LITEN, false, 60000),
	            new Bil("IJ7890", null, "Ford", "Focus", Color.GRAY, BilKategori.MELLOMSTOR, false, 85000),
	            
	            new Bil("KL1234", null, "Nissan", "Altima", Color.WHITE, BilKategori.STOR, false, 30000),
	            new Bil("MN5678", null, "Hyundai", "Santa Fe", Color.BLACK, BilKategori.STASJONSVOGN, false, 45000),
	            new Bil("OP9012", null, "Audi", "A4", Color.BLUE, BilKategori.MELLOMSTOR, false, 40000),
	            new Bil("QR3456", null, "Mazda", "CX-5", Color.RED, BilKategori.STOR, false, 52000),
	            new Bil("ST7890", null, "Chevrolet", "Impala", Color.SILVER, BilKategori.STOR, false, 90000),
	            
	            new Bil("UV1234", null, "Honda", "Civic", Color.GREEN, BilKategori.LITEN, false, 15000),
	            new Bil("WX5678", null, "Mercedes", "C-Class", Color.ORANGE, BilKategori.STASJONSVOGN, false, 7000),
	            new Bil("YZ9012", null, "Kia", "Sorento", Color.PURPLE, BilKategori.STASJONSVOGN, false, 22000),
	            new Bil("AB3456", null, "Subaru", "Outback", Color.GRAY, BilKategori.MELLOMSTOR, false, 67000),
	            new Bil("CD7890", null, "Jeep", "Cherokee", Color.YELLOW, BilKategori.LITEN, false, 49000),
	            
	            new Bil("EF1234", null, "Volvo", "XC90", Color.RED, BilKategori.MELLOMSTOR, false, 34000),
	            new Bil("GH5678", null, "Jaguar", "XE", Color.BLUE, BilKategori.STASJONSVOGN, false, 56000),
	            new Bil("IJ9012", null, "Lexus", "RX", Color.BLACK, BilKategori.MELLOMSTOR, false, 29000),
	            new Bil("KL3456", null, "Porsche", "Macan", Color.SILVER, BilKategori.STASJONSVOGN, false, 47000),
	            new Bil("MN7890", null, "Land Rover", "Range Rover", Color.GRAY, BilKategori.STASJONSVOGN, false, 110000)
	        };
		    
        for (int i = 0; i < biler.length; i++) {
        	int kontorIndex = i % 5;
            biler[i].setUtleieKontor(utleiekontorer.get(kontorIndex));
            utleiekontorer.get(kontorIndex).addBil(biler[i]);
        }
	}
	
	public static void addGeneratedReservations() {
		
        Random random = new Random();
    
        String[] fornavn = {"Ola", "Kari", "Per", "Lise", "Anna"};
        String[] etternavn = {"Nordmann", "Hansen", "Johansen", "Olsen", "Larsen"};
        String[] gateadresser = {"Hovedgata 10", "Bekkveien 3", "Fjellveien 15", "Strandgata 22", "Parkveien 5"};
        String[] poststeder = {"Oslo", "Bergen", "Trondheim", "Førde", "Kristiansand"};
        String[] postnummer = {"0101", "5000", "7000", "6800", "4612"};
        
        for (int i = 0; i < utleiekontorer.size(); i++) {
            // Generer en tilfeldig kunde
            String fnavn = fornavn[random.nextInt(fornavn.length)];
            String enavn = etternavn[random.nextInt(etternavn.length)];
            String gateadresse = gateadresser[random.nextInt(gateadresser.length)];
            String poststed = poststeder[random.nextInt(poststeder.length)];
            String postNr = postnummer[random.nextInt(postnummer.length)];
            Adresse adresse = new Adresse(gateadresse, postNr, poststed);
            Kunde kunde = new Kunde(fnavn, enavn, adresse, "12345678"); // Telefonnummer som placeholder
            kunder.add(kunde);

            // Generer tilfeldige hentetidspunkt og returpunkt (± 2 uker)
            LocalDateTime datoHent = LocalDateTime.now().minusDays(random.nextInt(14) + 1);
            LocalDateTime datoRetur = datoHent.plusDays(random.nextInt(14) + 1);
            
            // Velg tilfeldige kontorer for henting og retur
            Utleiekontor kontorHent = utleiekontorer.get(i % utleiekontorer.size());
            Utleiekontor kontorRetur = utleiekontorer.get(random.nextInt(utleiekontorer.size()));

            // Velg en tilfeldig bil fra kontoret
            Bil bil = kontorHent.getBiler().get(random.nextInt(kontorHent.getBiler().size()));

            // Beregn pris (f.eks. dagspris + gebyr mellom kontorer)
            int dager = (int) ChronoUnit.DAYS.between(datoHent, datoRetur);
            double dagspris = PrisKonfigurasjon.getDagspris(bil.getBilKategori());
            double totalPris = dager * dagspris;
            
            // Opprett reservasjonen
            Reservasjon reservasjon = new Reservasjon(totalPris, bil, kunde, kontorHent, kontorRetur, datoHent, datoRetur);

            // Legg til reservasjonen
            kontorHent.addReservasjon(reservasjon);
        }
		
	}
	
	/*
	
	
	public static void startApp() {
		boolean kjorProgram = true;
		while(kjorProgram) {
		    String velgBruker = showInputDialog("Velg brukersystem | 1 for Kunde | 2 for Konsulent | 3 for Admin | 4 for å stenge program");
			switch(velgBruker) {
			case "1":
				searchAndReserveCar();
				break;
			case "2":
				konsulentProgram();
				break;
			case "3":
				adminProgram();
				break;
			case "4":
				kjorProgram = false;
				break;
			default:
				System.out.println("Du må skrive ned tallet 1,2,3 eller 4 så trykke enter");	
			}	
				
		}
	}

	
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

	
public static void searchAndReserveCar() {
        
        // Initialize date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate startDate = null;
        LocalDate endDate = null;
        
        // Get start date
        while (startDate == null) {
            String startInput = JOptionPane.showInputDialog("Velg hentedato (YYYY-MM-DD):");
            if (startInput != null) { // User didn't press cancel
                try {
                    startDate = LocalDate.parse(startInput, formatter);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(null, "Vennligst oppgi i format YYYY-MM-DD.");
                }
            } else {
                return; // User pressed cancel
            }
        }
        
        // Get end date
        while (endDate == null) {
            String endInput = JOptionPane.showInputDialog("Fra dato: " + startDate + "\n" + "Velg returdato (YYYY-MM-DD):");
            if (endInput != null) { // User didn't press cancel
                try {
                    endDate = LocalDate.parse(endInput, formatter);
                    if (endDate.isBefore(startDate)) {
                        JOptionPane.showMessageDialog(null, "Returdato må være etter hentedato.");
                        endDate = null;
                    }
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(null, "Vennligst oppgi i format YYYY-MM-DD.");
                }
            } else {
                return; // User pressed cancel
            }
        }
        
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        // JOptionPane.showMessageDialog(null, "Selected date interval: " + startDate + " to " + endDate + " (" + days + " days)");
        
        // Get the selected rental office
        Kontor selectedUtleiekontor = null;
        while (selectedUtleiekontor == null) {
            String officeInput = JOptionPane.showInputDialog("Velg utleiekontor (OSLO, FORDE ELLER TRONDHEIM):");
            if (officeInput != null) { // User didn't press cancel
                try {
                    selectedUtleiekontor = Kontor.valueOf(officeInput.toUpperCase());
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Kontoret finnes ikke, vennligst velg OSLO, FORDE eller TRONDHEIM.");
                }
            } else {
                return; // User pressed cancel
            }
        }
        
        // Filter available cars
        final Kontor chosenUtleiekontor = selectedUtleiekontor;
        
        List<Bil> availableCars = getAllCars().stream()
                .filter(bil -> bil.getLedig() && bil.getUtleiekontor() == chosenUtleiekontor)
                .collect(Collectors.toList());
            
        
        int index = 1;
        StringBuilder carListMessage = new StringBuilder("Ledige biler er:\n");
        for (Bil bil : availableCars) {
            carListMessage.append(index + ") "+ bil.toString()).append("\n\n");
            index++;
        }
        
        carListMessage.append("/n").append("Velg liste nr. for å reservere bil");
          
       String valgtBilInput = JOptionPane.showInputDialog(carListMessage.toString());
       int parsedIndex = Integer.parseInt(valgtBilInput) - 1;
       
       Bil valgtBil = availableCars.get(parsedIndex);
        
       JOptionPane.showMessageDialog(null, "Du har valgt bil:" + valgtBil + "/n" + "");
       
       // 
       
       Kunde kundeData = getCustomerData();
       
       String kredittkort = JOptionPane.showInputDialog("Oppgi kredittkort nummer");

       registrerUtleie(kundeData, valgtBil, startDate.atStartOfDay(), endDate.atStartOfDay(), kredittkort);

        
    }

public static Kunde getCustomerData() {
    JPanel panel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
    panel.setLayout(boxLayout);

    JTextField navnField = new JTextField(20);
    JTextField gateadresseField = new JTextField(20);
    JTextField postNmrField = new JTextField(20);
    JTextField poststedField = new JTextField(20);
    JTextField tlfNmrField = new JTextField(20);

    panel.add(new JLabel("Navn:"));
    panel.add(navnField);
    panel.add(new JLabel("Gateadresse:"));
    panel.add(gateadresseField);
    panel.add(new JLabel("Postnummer:"));
    panel.add(postNmrField);
    panel.add(new JLabel("Poststed:"));
    panel.add(poststedField);
    panel.add(new JLabel("Telefonnummer:"));
    panel.add(tlfNmrField);

    int option = JOptionPane.showConfirmDialog(null, panel, "Legg til info", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (option == JOptionPane.OK_OPTION) {
        String navn = navnField.getText();
        String gateadresse = gateadresseField.getText();
        String postnummer = postNmrField.getText();
        String poststed = poststedField.getText();
        String tlfNmr = tlfNmrField.getText();

        Adresse adresse = new Adresse(gateadresse, postnummer, poststed);
        Kunde kunde = new Kunde(navn, adresse, tlfNmr);

        System.out.println(kunde);
        return kunde;
    }

    return null; 
}

public static void registrerUtleie(
        Kunde kunde, 
        Bil bil,
        LocalDateTime tidspunktLeige,
        LocalDateTime tidspunktRetur,
        String kredittkort
        ) {

    Utlaan nyttUtlaan = new Utlaan(
            tidspunktLeige, 
            kredittkort,
            bil.getRegistreringsNmr(),
            tidspunktRetur,
            kunde,
            bil,
            bil.getUtleiekontor()
            );

    utlaan.add(nyttUtlaan);
    System.out.println(utlaan);

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
	
	public static List<Bil> getAllCars() {
		
		
		
	
		
		
	}
	
	*/
	
}
