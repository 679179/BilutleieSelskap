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

public class BilutleieSelskap {

	private String navn;
	private Adresse firmaAdresse;
	private String tlfNmr;
	private static List<Bil> biler;
	private static List<Utlaan> utlaan;
	private static List<UtleieKontor> utleiekontor;
	
	
	public static void main(String[] args) {
		boolean kjorProgram = true;
		biler = new ArrayList<Bil>();
		utlaan = new ArrayList<Utlaan>();
		
		addGeneratedCars();


	        // ÅPNE GUI/TERMINAL (scanner eller javafx) 
			// VELG OM ER KUNDE ELLER KONSULENT (scanner velg 1,2,3 for hhv. kunde, konsulent, admin)
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
		System.out.println("Du må skrive 1 for Kunde 2 for Konsulent eller 3 for Admin");	
	}	
			
		}
		

		
	}
	
	public BilutleieSelskap() {
	}
	
	public BilutleieSelskap(String navn, Adresse firmaAdresse, String tlfNmr) {
		this.navn = navn;
		this.firmaAdresse = firmaAdresse;
		this.tlfNmr = tlfNmr;
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
		List<Bil> bil = biler.stream().filter(x -> x.getRegistreringsNmr().equals(regNmr) && x.getLedig() == false).toList();
		Bil funnetBil = bil.get(0);
		funnetBil.setLedig(true);
		LocalDateTime dato = LocalDateTime.now();
		
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
	
	public static void addGeneratedCars() {
        biler.add(new Bil("EL902018", "VW", Colour.BLACK, Utleiegruppe.B, true, Kontor.FORDE));
        biler.add(new Bil("AB123456", "Toyota", Colour.BLUE, Utleiegruppe.A, false, Kontor.OSLO));
        biler.add(new Bil("CD789012", "BMW", Colour.RED, Utleiegruppe.C, true, Kontor.TRONDHEIM));
        biler.add(new Bil("EF345678", "Audi", Colour.WHITE, Utleiegruppe.D, false, Kontor.FORDE));
        biler.add(new Bil("GH901234", "Mercedes", Colour.SILVER, Utleiegruppe.B, true, Kontor.OSLO));
        biler.add(new Bil("IJ567890", "Ford", Colour.BLACK, Utleiegruppe.B, true, Kontor.TRONDHEIM));
        biler.add(new Bil("KL123987", "Tesla", Colour.WHITE, Utleiegruppe.C, false, Kontor.FORDE));
        biler.add(new Bil("MN345678", "Volvo", Colour.GRAY, Utleiegruppe.A, true, Kontor.OSLO));
        biler.add(new Bil("OP901234", "Honda", Colour.BLUE, Utleiegruppe.D, false, Kontor.TRONDHEIM));
        biler.add(new Bil("QR567890", "Mazda", Colour.RED, Utleiegruppe.A, true, Kontor.FORDE));
        biler.add(new Bil("ST123456", "Nissan", Colour.SILVER, Utleiegruppe.B, false, Kontor.OSLO));
        biler.add(new Bil("UV789012", "Hyundai", Colour.BLACK, Utleiegruppe.C, true, Kontor.TRONDHEIM));
        biler.add(new Bil("WX345678", "Peugeot", Colour.WHITE, Utleiegruppe.A, false, Kontor.FORDE));
        biler.add(new Bil("YZ901234", "Kia", Colour.GRAY, Utleiegruppe.D, true, Kontor.OSLO));
        biler.add(new Bil("AC567890", "Renault", Colour.BLUE, Utleiegruppe.D, false, Kontor.TRONDHEIM));
        biler.add(new Bil("BD123456", "Subaru", Colour.RED, Utleiegruppe.B, true, Kontor.FORDE));
        biler.add(new Bil("CE789012", "Fiat", Colour.SILVER, Utleiegruppe.C, false, Kontor.OSLO));
        biler.add(new Bil("DF345678", "Citroen", Colour.BLACK, Utleiegruppe.A, true, Kontor.TRONDHEIM));
        biler.add(new Bil("EG901234", "Jaguar", Colour.WHITE, Utleiegruppe.D, false, Kontor.FORDE));
        biler.add(new Bil("FH567890", "Lexus", Colour.GRAY, Utleiegruppe.A, true, Kontor.OSLO));
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
        
        List<Bil> availableCars = biler.stream()
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
    	UtleieKontor kontor1 = new UtleieKontor(Integer.parseInt(kontorNmr.toString()), adresse1, tlfNmr.toString());
    	utleiekontor.add(kontor1);
    	
        // Output the collected data (or process it as needed)
        System.out.println(kontorData);
    }
    
}
	
	public static LocalDateTime getLocalTime() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");
		LocalDateTime tidspunkt = LocalDateTime.now();
		
		return tidspunkt;
	}
	
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Adresse getFirmaAdresse() {
		return firmaAdresse;
	}

	public void setFirmaAdresse(Adresse firmaAdresse) {
		this.firmaAdresse = firmaAdresse;
	}

	public String getTlfNmr() {
		return tlfNmr;
	}

	public void setTlfNmr(String tlfNmr) {
		this.tlfNmr = tlfNmr;
	}

	public static List<Bil> getBiler() {
		return biler;
	}

	public static void setBiler(List<Bil> biler) {
		BilutleieSelskap.biler = biler;
	}

	public static List<Utlaan> getUtlaan() {
		return utlaan;
	}

	public static void setUtlaan(List<Utlaan> utlaan) {
		BilutleieSelskap.utlaan = utlaan;
	}

	
	
}
