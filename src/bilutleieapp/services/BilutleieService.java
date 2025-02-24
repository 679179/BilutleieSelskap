package bilutleieapp.services;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import bilutleieapp.enums.BilKategori;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.repository.Repository;

public class BilutleieService {
	
    private static BilutleieService instance;
    private Repository repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
        String[] options = repository.getLokasjoner().toArray(new String[0]); 
    	return UIHelper.chooseUserActionFromOptions("Velg tilhørende lokasjon for ditt kontor", "Lokasjon Valg", options);
    }
    
	public String getLocationInputFromUser(String promptMsg) {
		
        final JDialog dialog = new JDialog((Frame) null, promptMsg, true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(400, 200);

        final String[] selectedLocation = {null};
       

        for (String lokasjon : repository.getLokasjoner()) {
            JButton button = new JButton(lokasjon);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedLocation[0] = lokasjon;
                    dialog.dispose();
                }
            });
            dialog.add(button);
        }

        dialog.setVisible(true);
        return selectedLocation[0];
	}
	
    public Map<String, Object> getParamsForReservationSearch() {
    	
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
