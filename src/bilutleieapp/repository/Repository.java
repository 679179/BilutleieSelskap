package bilutleieapp.repository;
import java.util.ArrayList;
import java.util.List;
import bilutleieapp.entities.Reservasjon;
import bilutleieapp.entities.BilutleieSelskap;
import bilutleieapp.entities.Kunde;
import bilutleieapp.entities.Utleiekontor;
import bilutleieapp.helpers.UIHelper;

public class Repository {

	private BilutleieSelskap bilutleieselskap;
    private List<Utleiekontor> utleiekontorer;
    private List<Kunde> kunder;
	private List<String> lokasjoner;
    private static Repository instance;

    // Privat konstruktør for singleton
    private Repository() {
        this.utleiekontorer = new ArrayList<>();
        this.kunder = new ArrayList<>();
        this.lokasjoner = new ArrayList<>();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }
    
    // Selskap
    
    public BilutleieSelskap getBilutleieselskap() {
		return bilutleieselskap;
	}

	public void setBilutleieselskap(BilutleieSelskap bilutleieselskap) {
		this.bilutleieselskap = bilutleieselskap;
	}
    
    // Utleiekontor metoder
    public List<Utleiekontor> getAllUtleiekontorer() {
        return utleiekontorer; 
    }


    public void addUtleiekontor(Utleiekontor kontor) {
        utleiekontorer.add(kontor);
    }

    public void removeUtleiekontor(Utleiekontor kontor) {
        utleiekontorer.remove(kontor);
    }
    
    public List<Utleiekontor> getUtleiekontorer() {
    	return utleiekontorer;
    }

    // Kunde metoder
    public List<Kunde> getAllKunder() {
        return kunder;
    }

    public void addKunde(Kunde kunde) {
        kunder.add(kunde);
    }

    public void removeKunde(Kunde kunde) {
        kunder.remove(kunde);
    }

    // Lokasjon metoder
    public List<String> getLokasjoner() {
        return lokasjoner;
    }
    
    public void setLokasjoner(List<String> lokasjoner) {
    	this.lokasjoner = lokasjoner;
    }

    public void addLokasjon(String lokasjon) {
        lokasjoner.add(lokasjon);
    }

    public void removeLokasjon(String lokasjon) {
        lokasjoner.remove(lokasjon);
    }

    public Utleiekontor findUtleiekontorByLocation(String lokasjon) {
		Utleiekontor kontor = getAllUtleiekontorer().stream()
		        .filter(k -> k.getLokasjon().toUpperCase().equals(lokasjon.toUpperCase()))
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("Utleiekontor med lokasjon " + lokasjon + " ikke funnet"));
		return kontor;
    }
    
    public String getExistingLocationFromUser() {
        String[] options = lokasjoner.toArray(new String[0]); 
    	return UIHelper.getValueFromDropdown("Velg tilhørende lokasjon for ditt kontor", "Lokasjon Valg", options);
    }
    
    public Reservasjon findReservationAtKontorByLastnameAndRegNr(Utleiekontor kontor, String etternavn, String regNr) {
		return kontor.getReservasjoner().stream()
	        .filter(res -> res.getBil().getRegistreringsNr().toUpperCase().equals(regNr.toUpperCase()) 
	                && res.getKunde().getEtternavn().toUpperCase().equals(etternavn.toUpperCase()))
	        .findFirst()
	        .orElseThrow(() -> new RuntimeException("Reservasjon ikke funnet for bil med registreringsnummer " + regNr + " og etternavn " + etternavn));
    }
}

