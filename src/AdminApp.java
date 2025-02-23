import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AdminApp {
	public static void start() {
		selectAction();
	}
    private static void selectAction() {
        AdminHandling[] options = {
        		AdminHandling.OPPRETT_UTLEIEKONTOR,
        		AdminHandling.REGISTRER_NY_BIL,
            };
        	AdminHandling selectedHandling = (AdminHandling) JOptionPane.showInputDialog(
                    null,
                    "Velg handling",
                    "Admin Valg",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (selectedHandling == null) return;
            switch (selectedHandling) {
                case OPPRETT_UTLEIEKONTOR:
                	registerNewUtleiekontor();
                    break;
                case REGISTRER_NY_BIL:
                	registerNewCar();
                    break;
                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
            }
    	
    }
    
    
    private static void registerNewUtleiekontor() {
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
            BilutleieSelskap.addNewUtleiekontor(newKontor);
            JOptionPane.showMessageDialog(null, "Ditt nye utleiekontor ved " + lokasjon + " er registrert!" );
    		BilutleieSelskap.startApp();
        }
    }
    
    private static void registerNewCar() {
    	String lokasjon = Utils.getExistingLocationFromUser();
    	Utleiekontor kontor = Utils.findUtleiekontorByLocation(lokasjon);
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
		BilutleieSelskap.startApp();
    }


}
