package bilutleieapp.services;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import bilutleieapp.entities.Bil;
import bilutleieapp.entities.Reservasjon;
import bilutleieapp.entities.Utleiekontor;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.repository.Repository;

public class KonsulentService {
	
    private static KonsulentService instance;
    private Repository repository;

    private KonsulentService() {

    }

    public static synchronized KonsulentService getInstance() {
        if (instance == null) {
            instance = new KonsulentService();
        }
        return instance;
    }
    
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    
  
    public String getExistingLocationFromUser() {
        return repository.getExistingLocationFromUser();
    }

	public Utleiekontor findUtleiekontorByLocation(String location) {
		Utleiekontor kontor = repository.getUtleiekontorer().stream()
		        .filter(k -> k.getLokasjon().toUpperCase().equals(location.toUpperCase()))
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("Utleiekontor med lokasjon " + location + " ikke funnet"));
		return kontor;
	}
	
	public void registerCarPickup() {
        Reservasjon reservasjon = getReservation();
        String creditCardNo = getCreditcardNo(reservasjon);
        LocalDateTime datoHent = UIHelper.getDateFromUser("Legg inn dato og klokkeslett for utlevering (YYYY-MM-DD HH:MM)");
        reservasjon.getKunde().setKredittkort(creditCardNo);
        String bekrStr = reservasjon.getBil().getMerke() + " med reg.nr " + reservasjon.getBil().getRegistreringsNr() + " er bekreftet utlevert for dato: " + datoHent;
        JOptionPane.showMessageDialog(null, bekrStr, "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String[] getCustomerAndCarInfo() {
        List<String> fieldLabels = Arrays.asList(
              "Etternavn:", "Reg.nr:"
            );
        Map<String, String> userInputs = UIHelper.getUserInputForFields(fieldLabels);
        if(userInputs == null) return null;
        String etternavn = userInputs.get("Etternavn:");
        String regNr = userInputs.get("Reg.nr:");
        return new String[] {etternavn, regNr};
    }
	
	public Reservasjon getReservation() {
		String location = getExistingLocationFromUser();
		String[] customerAndCarInfo = getCustomerAndCarInfo();
		String etternavn = customerAndCarInfo[0];
		String regNr = customerAndCarInfo[1];
		Utleiekontor kontor = findUtleiekontorByLocation(location);
		Reservasjon reservasjon = repository.findReservationAtKontorByLastnameAndRegNr(kontor, etternavn, regNr);
		if (reservasjon == null) {
			UIHelper.showError("Ingen reservasjon funnet for bil med registreringsnummer " + regNr + " og etternavn " + etternavn);
	        return null;
	    }
		return reservasjon;
	}
	
	public String getCreditcardNo(Reservasjon reservasjon) {
		String displayMsg = "Reservasjon funnet: " + reservasjon.toString() + "\nLegg inn kredittkort nummer til kunde:";
		String buttonConfirmText = "Bekreft utlevering av bil";
		return UIHelper.getUserInputFromField(displayMsg, buttonConfirmText);
	}
	
	public String getKmStand() {
		String displayMsg = "Legg inn km. stand til bilen";
		String buttonConfirmText = "Bekreft km stand";
		return UIHelper.getUserInputFromField(displayMsg, buttonConfirmText);	    
	}
	
	public void registerCarReturn() {
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
	}
}
