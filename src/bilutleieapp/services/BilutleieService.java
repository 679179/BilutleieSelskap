package bilutleieapp.services;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import bilutleieapp.entities.Adresse;
import bilutleieapp.entities.Bil;
import bilutleieapp.entities.Kunde;
import bilutleieapp.entities.Reservasjon;
import bilutleieapp.entities.Utleiekontor;
import bilutleieapp.enums.BilKategori;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.repository.Repository;

public class BilutleieService {
	
    private static BilutleieService instance;
    private Repository repository;
    private static final Map<BilKategori, Integer> dagspriser = new HashMap<>();
    private static final Map<Set<String>, Integer> gebyrer = new HashMap<>();

    private BilutleieService() {
    	initPriser();
    }

    public static synchronized BilutleieService getInstance() {
        if (instance == null) {
            instance = new BilutleieService();
        }
        return instance;
    }
    
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    
    public void initPriser() {
        dagspriser.put(BilKategori.LITEN, 1000);
        dagspriser.put(BilKategori.MELLOMSTOR, 2000);
        dagspriser.put(BilKategori.STOR, 3000);
        dagspriser.put(BilKategori.STASJONSVOGN, 4000);
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
    
    public String getExistingLocationFromUser() {
        return repository.getExistingLocationFromUser();
    }
    
	public String getLocationInputFromUser(String promptMsg) {
		return UIHelper.getUserOptionFromButtonClicked(promptMsg, repository.getLokasjoner());        
	}
	
    public Map<String, Object> getParamsForAvailibleCarsSearch() {
        LocalDateTime pickupDate = null;
        pickupDate = UIHelper.getDateFromUser("Vennligst oppgi dato for hent");
        LocalDateTime returnDate = null;
        returnDate = UIHelper.getDateFromUser("Vennligst oppgi dato for retur");
        
        if (pickupDate == null || returnDate == null ||  returnDate.isBefore(pickupDate)) {
            UIHelper.showError("Returdato må være etter hentedato. Begge felt må fylles ut");
            return null;
        }
        String chosenPickUpLocation = getLocationInputFromUser("Velg utleiekontor for henting av bil");
        String chosenReturnLocation = getLocationInputFromUser("Velg utleiekontor for retur av bil");
        Map<String, Object> searchParams = new HashMap<>();
            
        searchParams.put("pickupDate", pickupDate);
        searchParams.put("returnDate", returnDate);
        searchParams.put("chosenPickUpLocation", chosenPickUpLocation);
        searchParams.put("chosenReturnLocation", chosenReturnLocation);
        return searchParams;
    }
    
	public Bil getCarPickedFromAvailibleCars(LocalDateTime pickupDate,LocalDateTime returnDate, String chosenPickUpLocation, String chosenReturnLocation) {	
		
		List<Bil> availibleCars = repository.getAllUtleiekontorer()
				.stream()
				.filter(kontor -> kontor.getLokasjon() == chosenPickUpLocation)
				.toList()
				.get(0)
				.getBiler()
				.stream()
				.filter(bil -> bil.erLedigForDatoer(pickupDate, returnDate))
				.toList();
		
		if (availibleCars.size() == 0) {
			UIHelper.showMessage("Ingen tilgjengelige biler for valgt periode ved " + chosenPickUpLocation);
		    return null;
		}
				
	    final JDialog dialog = new JDialog(
	    		(Frame) null, 
	    		"Vi har følgende biler tilgjengelig ved " + chosenPickUpLocation + "for valgt periode. Vennligst klikk på den du vil reservere", 
	    		true);
	    
	    dialog.setLayout(new GridLayout(0, 1));
	    dialog.setSize(600, 400);
	    
	    final Bil[] chosenCar = {null};
	    
	    for (Bil bil : availibleCars) {
	        double carPrice = getCaluclatedPriceForCarCategory(bil.getBilKategori(), pickupDate, returnDate, chosenPickUpLocation, chosenReturnLocation);
	        JButton button = new JButton(bil.toString() + " - Pris: " + carPrice + "kr");
	        button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                chosenCar[0] = bil;
	                dialog.dispose();
	            }
	        });
	        dialog.add(button);
	    }
	    
	    dialog.setVisible(true);
	    return chosenCar[0];
	     
	}
	
    public Kunde getCustomerInfoForReservation() {
        List<String> fieldLabels = Arrays.asList(
            "Fornavn:", "Etternavn:", "Gateadresse:", "Postnummer:", "Poststed:", "Telefonnummer:"
        );
        Map<String, String> userInputs = UIHelper.getUserInputForFields(fieldLabels);
        if(userInputs == null) return null;
        String fornavn = userInputs.get("Fornavn:");
        String etternavn = userInputs.get("Etternavn:");
        String gateadresse = userInputs.get("Gateadresse:");
        String postnummer = userInputs.get("Postnummer:");
        String poststed = userInputs.get("Poststed:");
        String tlfNmr = userInputs.get("Telefonnummer:");
        Adresse adresse = new Adresse(gateadresse, postnummer, poststed);
        Kunde kunde = new Kunde(fornavn, etternavn, adresse, tlfNmr);
        return kunde;

    }
	
	public double getCaluclatedPriceForCarCategory(
			BilKategori bilkategori, 
			LocalDateTime henteDato, 
			LocalDateTime returDato, 
			String henteLokasjon, 
			String returLokasjon) 
		{
	    long days = ChronoUnit.DAYS.between(henteDato, returDato);
	    int dagspris = getDagspris(bilkategori);
	    int totalPris = (int) (days * dagspris);
	    if (!henteLokasjon.equals(returLokasjon)) {
	        int gebyr = getGebyr(henteLokasjon, returLokasjon);
	        totalPris += gebyr;
	    }
	    return totalPris;
	}
	
	public void addReservation(
			double pris, 
			Bil bil, 
			Kunde kunde, 
			String kontorHent, 
			String kontorRetur,
			LocalDateTime datoHent, 
			LocalDateTime datoRetur
			) {
		bil.addReservasjonsDato(datoRetur, datoHent);
		Reservasjon nyReservasjon = new Reservasjon(
				pris, 
				bil, 
				kunde, 
				kontorHent, 
				kontorRetur,
				datoHent, 
				datoRetur
		);
		Utleiekontor correspondingUtleiekontor = repository.getAllUtleiekontorer().stream().filter(kontor -> kontor.getLokasjon() == kontorHent).toList().get(0);
		correspondingUtleiekontor.addReservasjon(nyReservasjon);
		UIHelper.showMessage("Reservasjon på \" + bil.getMerke() + \" med reg.nr \" + bil.getRegistreringsNr() +  \" er registrert!");
	}
	
    private Set<String> createLokasjonPair(String lok1, String lok2) {
        Set<String> lokasjonPair = new TreeSet<>();
        lokasjonPair.add(lok1);
        lokasjonPair.add(lok2);
        return lokasjonPair;
    }

    public int getDagspris(BilKategori kategori) {
        return dagspriser.getOrDefault(kategori, 0); 
    }

    public int getGebyr(String lok1, String lok2) {
        Set<String> lokasjonPair = createLokasjonPair(lok1, lok2);
        return gebyrer.getOrDefault(lokasjonPair, 0); 
    }
    
}
