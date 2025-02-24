package bilutleieapp.apps;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bilutleieapp.entities.Bil;
import bilutleieapp.entities.Reservasjon;
import bilutleieapp.entities.Utleiekontor;
import bilutleieapp.enums.KonsulentHandling;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.repository.Repository;
import bilutleieapp.services.BilutleieService;

import java.time.LocalDateTime;


public class KonsulentApp implements App {
	
	private static KonsulentApp instance;
    private BilutleieService bilutleieService;
    private Repository repo;
    private AppManager appManager;

    private KonsulentApp() {

    }
    
    public static KonsulentApp getInstance() {
        if (instance == null) {
            instance = new KonsulentApp();
        }
        return instance;
    }
    
    @Override
    public void setBilutleieService(BilutleieService bilutleieService) {
    	this.bilutleieService = bilutleieService;
    }
    @Override
    public void setRepository(Repository repo) {
    	this.repo = repo;
    }
    @Override
    public void setAppManager(AppManager appManager) {
    	this.appManager = appManager;
    }
    
    @Override
    public void start() {    
    	redirectToApp();
    }
    private void redirectToApp() {
    	KonsulentHandling handling = UIHelper.chooseUserActionFromEnumOptions("Velg handling for konsulentrolle", "Konsulent valg", KonsulentHandling.values());
        if (handling == null) return;
        switch (handling) {
            case RESERVER_BIL -> appManager.getBilReservasjonApp().start();
            case REGISTRER_HENTING -> registerCarPickup();
            case REGISTRER_RETUR -> registerCarReturn();
            default -> System.out.println("Ugyldig valg, prøv igjen.");
        }
    }
	
	private void registerCarPickup() {
        Reservasjon reservasjon = getReservation();
        String creditCardNo = getCreditcardNo(reservasjon);
        LocalDateTime datoHent = UIHelper.getDateFromUser("Legg inn dato og klokkeslett for utlevering (YYYY-MM-DD HH:MM)");
        reservasjon.getKunde().setKredittkort(creditCardNo);
        String bekrStr = reservasjon.getBil().getMerke() + " med reg.nr " + reservasjon.getBil().getRegistreringsNr() + " er bekreftet utlevert for dato: " + datoHent;
        JOptionPane.showMessageDialog(null, bekrStr, "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
        appManager.startApp();
	}
	
	public String[] getCustomerAndCarInfo() {
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
	
	private Reservasjon getReservation() {
		String location = bilutleieService.getExistingLocationFromUser();
		String[] customerAndCarInfo = getCustomerAndCarInfo();
		String etternavn = customerAndCarInfo[0];
		String regNr = customerAndCarInfo[1];
		Utleiekontor kontor = repo.findUtleiekontorByLocation(location);
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
	
	private String getCreditcardNo(Reservasjon reservasjon) {
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
	
	private String getKmStand() {
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
	
	
	private void registerCarReturn() {
		Reservasjon reservasjon = getReservation();
		LocalDateTime datoRetur = UIHelper.getDateFromUser("Legg inn dato og klokkeslett for retur (YYYY-MM-DD HH:MM)");
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
        appManager.startApp();
	}

}
