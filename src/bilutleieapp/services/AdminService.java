package bilutleieapp.services;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bilutleieapp.entities.Adresse;
import bilutleieapp.entities.Bil;
import bilutleieapp.entities.Utleiekontor;
import bilutleieapp.enums.BilKategori;
import bilutleieapp.enums.Color;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.repository.Repository;

public class AdminService implements Service {
	
    private static AdminService instance;
    private Repository repository;

    private AdminService() {

    }

    public static synchronized AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }
    
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    
    public void registerNewUtleiekontor() {
        List<String> fieldLabels = Arrays.asList(
                "Lokasjon:", "Gateadresse:", "Postnummer:", "Poststed:", "Kontor nr:", "Telefon nr:"
            );
        Map<String, String> userInputs = UIHelper.getUserInputForFields(fieldLabels);
        if(userInputs == null) return;
        String lokasjon = userInputs.get("Lokasjon:");
        String gateadresse = userInputs.get("Gateadresse:");
        String postNmr = userInputs.get("Postnummer:");
        String poststed = userInputs.get("Poststed:");
        String tlfNmr = userInputs.get("Telefon nr:");
        int kontorNr = Integer.parseInt(userInputs.get("Kontor nr:"));
        Adresse adresse = new Adresse(gateadresse, postNmr, poststed);
        Utleiekontor newKontor = new Utleiekontor(lokasjon, kontorNr, adresse, tlfNmr);
        repository.addUtleiekontor(newKontor);
        UIHelper.showMessage("Ditt nye utleiekontor ved " + lokasjon + " er registrert!");
    }
    
    public void registerNewCar() {
    	String lokasjon = repository.getExistingLocationFromUser();
    	Utleiekontor kontor = repository.findUtleiekontorByLocation(lokasjon);
        List<String> fieldLabels = Arrays.asList(
                "Merke:", "Modell:", "Regnr:", "Km stand:"
        );
        Map<String, String> userInputs = UIHelper.getUserInputForFields(fieldLabels);
        BilKategori bilKategori = UIHelper.getValueFromDropdownEnums("Velg bilkategori", "Bilkategori", BilKategori.values());
        Color farge = UIHelper.getValueFromDropdownEnums("Velg farge for bilen", "Frage", Color.values());
        String merke = userInputs.get("Merke:");
        String modell = userInputs.get("Modell:");
        String regNr = userInputs.get("Regnr:");
        int kmStand = Integer.parseInt(userInputs.get("Km stand:"));
        Bil bil = new Bil(regNr, kontor, merke, merke, farge, bilKategori, kmStand);
        kontor.addBil(bil);
        UIHelper.showMessage(merke + " er registert ved " + lokasjon + "!");
    }

}
