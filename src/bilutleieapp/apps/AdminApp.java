package bilutleieapp.apps;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bilutleieapp.entities.Adresse;
import bilutleieapp.entities.Bil;
import bilutleieapp.entities.Utleiekontor;
import bilutleieapp.enums.AdminHandling;
import bilutleieapp.enums.BilKategori;
import bilutleieapp.enums.Color;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.repository.Repository;
import bilutleieapp.services.BilutleieService;

public class AdminApp implements App {
	
	private static AdminApp instance;
    private Repository repo;
    private BilutleieService bilutleieService;
    private AppManager appManager;
 
    private AdminApp() {

    }
    
    public static AdminApp getInstance() {
        if (instance == null) {
            instance = new AdminApp();
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
		selectAction();
	}
    private void selectAction() {
    	AdminHandling handling = UIHelper.chooseUserActionFromEnumOptions("Velg handling for adminprogram", "Admin valg", AdminHandling.values());
        switch (handling) {
	        case OPPRETT_UTLEIEKONTOR -> registerNewUtleiekontor();
	        case REGISTRER_NY_BIL -> registerNewCar();
	        default -> System.out.println("Ugyldig valg, prøv igjen.");
        }
    }
    
    
    private void registerNewUtleiekontor() {
        JTextField lokasjonField = new JTextField();
        JTextField gateadresseField = new JTextField();
        JTextField postNmrField = new JTextField();
        JTextField poststedField = new JTextField();
        JTextField kontorNrField = new JTextField();
        JTextField tlfNrField = new JTextField();
        Object[] message = {
            "Lokasjon:", lokasjonField,
            "Gateadresse:", gateadresseField,
            "Postnummer:", postNmrField,
            "Poststed:", poststedField,
            "Kontor nr:", kontorNrField,
            "Telefon nr:", tlfNrField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Register Nytt Utleiekontor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String lokasjon = lokasjonField.getText();
            String gateadresse = gateadresseField.getText();
            String postNmr = postNmrField.getText();
            String poststed = poststedField.getText();
            String tlfNr = tlfNrField.getText();
            int kontorNr = Integer.parseInt(kontorNrField.getText());
            
            if (lokasjon.isEmpty() || gateadresse.isEmpty() || postNmr.isEmpty() || poststed.isEmpty() || tlfNr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alle feltene må fylles ut!", "Feil", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Adresse adresse = new Adresse(gateadresse, postNmr, poststed);
            Utleiekontor newKontor = new Utleiekontor(lokasjon, kontorNr, adresse, tlfNr);
            repo.addUtleiekontor(newKontor);
            JOptionPane.showMessageDialog(null, "Ditt nye utleiekontor ved " + lokasjon + " er registrert!" );
            appManager.startApp();
        }
    }
    
    private void registerNewCar() {
    	String lokasjon = bilutleieService.getExistingLocationFromUser();
    	Utleiekontor kontor = repo.findUtleiekontorByLocation(lokasjon);
        JTextField registreringsNrField = new JTextField();
        JTextField merkeField = new JTextField();
        JTextField modellField = new JTextField();
        JTextField fargeField = new JTextField();
        JTextField bilKategoriField = new JTextField();
        JTextField kmStandField = new JTextField();
        Object[] message = {
            "registreringsNr:", registreringsNrField,
            "merke:", merkeField,
            "modell:", modellField,
            "farge:", fargeField,
            "bilKategori (LITEN, MELLOMSTOR, STOR, STASJONSVOGN):", bilKategoriField,
            "kmStand", kmStandField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Registrer ny bil", JOptionPane.OK_CANCEL_OPTION);
        if(option != JOptionPane.OK_OPTION) return;
        String registreringsNr = registreringsNrField.getText();
        String merke = merkeField.getText();
        String modell = modellField.getText();
        Color farge = Color.valueOf(fargeField.getText().toUpperCase());
        BilKategori bilKategori = BilKategori.valueOf(bilKategoriField.getText().toUpperCase());
        int kmStand = Integer.parseInt(kmStandField.getText());
        if (registreringsNr.isEmpty() || merke.isEmpty() || modell.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alle feltene må fylles ut!", "Feil", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Bil bil = new Bil(registreringsNr, kontor, merke, merke, farge, bilKategori, kmStand);
        kontor.addBil(bil);
        JOptionPane.showMessageDialog(null, merke + " er registert ved " + lokasjon + "!" );
        appManager.startApp();
    }


}
