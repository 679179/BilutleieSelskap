import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.time.LocalDateTime;


public class KonsulentApp {
    public static void start() {    
        selectAction();
    }
    private static void selectAction() {
        KonsulentHandling[] options = {
                KonsulentHandling.RESERVER_BIL,
                KonsulentHandling.REGISTRER_HENTING,
                KonsulentHandling.REGISTRER_RETUR
            };
            KonsulentHandling selectedHandling = (KonsulentHandling) JOptionPane.showInputDialog(
                    null,
                    "Velg handling",
                    "Konsulent Valg",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (selectedHandling == null) return;
            switch (selectedHandling) {
                case RESERVER_BIL:
                    BilReservasjonApp.start();
                    break;
                case REGISTRER_HENTING:
                    registerCarPickup();
                    break;
                case REGISTRER_RETUR:
                    registerCarReturn();
                    break;
                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
                    BilutleieSelskap.startApp();
            }
    }
	
	private static void registerCarPickup() {
        Reservasjon reservasjon = getReservation();
        String creditCardNo = getCreditcardNo(reservasjon);
        LocalDateTime datoHent = Utils.getDateAndTimeFromUser("Legg inn dato og klokkeslett for utlevering (YYYY-MM-DD HH:MM)");
        reservasjon.getKunde().setKredittkort(creditCardNo);
        String bekrStr = reservasjon.getBil().getMerke() + " med reg.nr " + reservasjon.getBil().getRegistreringsNr() + " er bekreftet utlevert for dato: " + datoHent;
        JOptionPane.showMessageDialog(null, bekrStr, "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
		BilutleieSelskap.startApp();
	}
	
	public static String[] getCustomerAndCarInfo() {
        JTextField lastNameField = new JTextField();
        JTextField registrationNumberField = new JTextField();
        Object[] message = {
            "Etternavn:", lastNameField,
            "Registreringsnummer:", registrationNumberField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Oppgi kundens etternavn og reg.nr til bilen", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String lastName = lastNameField.getText();
            String registrationNumber = registrationNumberField.getText();
            if (lastName.isEmpty() || registrationNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Begge feltene må fylles ut!", "Feil", JOptionPane.ERROR_MESSAGE);
                return null;
            } else {
                return new String[] {lastName, registrationNumber};
            }
        } else {
            return null;
        }
    }
	
	private static Reservasjon getReservation() {
		String location = Utils.getExistingLocationFromUser();
		String[] customerAndCarInfo = getCustomerAndCarInfo();
		String etternavn = customerAndCarInfo[0];
		String regNr = customerAndCarInfo[1];
		Utleiekontor kontor = Utils.findUtleiekontorByLocation(location);
		Reservasjon reservasjon = kontor.getReservasjoner().stream()
		        .filter(res -> res.getBil().getRegistreringsNr().toUpperCase().equals(regNr.toUpperCase()) 
		                && res.getKunde().getEtternavn().toUpperCase().equals(etternavn.toUpperCase()))
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("Reservasjon ikke funnet for bil med registreringsnummer " + regNr + " og etternavn " + etternavn));
		if (reservasjon == null) {
	        JOptionPane.showMessageDialog(null, "Ingen reservasjon funnet for bil med registreringsnummer " + regNr + " og etternavn " + etternavn, "Feil", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
		return reservasjon;

	}
	
	private static String getCreditcardNo(Reservasjon reservasjon) {
	    JTextField creditCardField = new JTextField();
	    Object[] message = {
	        "Reservasjon funnet: " + reservasjon.toString() + "\n\nLegg inn kredittkort nummer til kunde:",
	        creditCardField
	    };
	    int option = JOptionPane.showConfirmDialog(null, message, "Bekreft utlevering av bil", JOptionPane.OK_CANCEL_OPTION);
	    String ccNo = null;
	    if (option == JOptionPane.OK_OPTION) {
	    	ccNo = creditCardField.getText();
	        if (ccNo.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Kredittkortnummeret må fylles ut!", "Feil", JOptionPane.ERROR_MESSAGE);
	        } 
	    }
	    return ccNo;
	}
	
	private static String getKmStand() {
	    JTextField kmStandField = new JTextField();
	    Object[] message = {
	        "Legg inn km. stand til bilen",
	        kmStandField
	    };
	    int option = JOptionPane.showConfirmDialog(null, message, "Bekreft km stand", JOptionPane.OK_CANCEL_OPTION);
	    String kmStand = null;
	    if (option == JOptionPane.OK_OPTION) {
	    	kmStand = kmStandField.getText();
	        if (kmStand.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Kredittkortnummeret må fylles ut!", "Feil", JOptionPane.ERROR_MESSAGE);
	        } 
	    }
	    return kmStand;
	}
	
	
	private static void registerCarReturn() {
		Reservasjon reservasjon = getReservation();
		LocalDateTime datoRetur = Utils.getDateAndTimeFromUser("Legg inn dato og klokkeslett for retur (YYYY-MM-DD HH:MM)");
		String kmStand = getKmStand();
		Bil bil = reservasjon.getBil();
		int kmKjort = Integer.parseInt(kmStand)-bil.getKmStand();
		reservasjon.setDatoFaktiskRetur(datoRetur);
		reservasjon.setSkydligBelop(reservasjon.getPris());
        String bekrStr = 
        		bil.getMerke() + 
        		" med reg.nr " + bil.getRegistreringsNr() + 
        		" er bekreftet returnert for dato : " + datoRetur + ". \n" +
        		"Antall km kjørt under leie var: " + kmKjort;
        JOptionPane.showMessageDialog(null, bekrStr, "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
		BilutleieSelskap.startApp();
	}

}
