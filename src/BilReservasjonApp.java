
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BilReservasjonApp {	

    public static void start() {
        Map<String, Object> searchParams = getSearchParamsFromUser();
        LocalDateTime pickupDate = (LocalDateTime) searchParams.get("pickupDate");
        LocalDateTime returnDate = (LocalDateTime) searchParams.get("returnDate");
        String chosenPickUpLocation = (String) searchParams.get("chosenPickUpLocation");
        String chosenReturnLocation = (String) searchParams.get("chosenReturnLocation");
        Bil carPicked = getCarPickedFromAvailible(pickupDate, returnDate, chosenPickUpLocation, chosenReturnLocation);
        Kunde customer = getCustomerInfoForReservation();
        if(carPicked == null || customer == null) return;
        double priceCalculated = getCaluclatedPriceForCar(
        		carPicked.getBilKategori(), 
        		pickupDate, 
        		returnDate, 
        		chosenPickUpLocation, 
        		chosenReturnLocation);
    	confirmReservation(
    			priceCalculated,
    			carPicked,
    			customer, 
    			chosenPickUpLocation, 
    			chosenReturnLocation,
    			pickupDate,
    			returnDate
    	);	        
    }
    
    private static Map<String, Object> getSearchParamsFromUser() {
        LocalDateTime pickupDate = null;
        while (pickupDate == null) {
            pickupDate = Utils.getDateAndTimeFromUser("Vennligst oppgi hentedato, i format YYYY-MM-DD HH:MM \n f.eks. 2025-02-22 18:30");
        }
        LocalDateTime returnDate = null;
        while (returnDate == null) {
            returnDate = Utils.getDateAndTimeFromUser("Vennligst oppgi returdato, i format YYYY-MM-DD HH:MM \n f.eks. 2025-02-22 10:00");
            if (returnDate != null && returnDate.isBefore(pickupDate)) {
                JOptionPane.showMessageDialog(null, "Returdato må være etter hentedato.");
                returnDate = null;
            }
        }
        String chosenPickUpLocation = Utils.getLocationFromUser("Velg utleiekontor for henting av bil");
        String chosenReturnLocation = Utils.getLocationFromUser("Velg utleiekontor for retur av bil");
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("pickupDate", pickupDate);
        searchParams.put("returnDate", returnDate);
        searchParams.put("chosenPickUpLocation", chosenPickUpLocation);
        searchParams.put("chosenReturnLocation", chosenReturnLocation);
        return searchParams;
    }
		
	private static Bil getCarPickedFromAvailible(LocalDateTime pickupDate,LocalDateTime returnDate, String chosenPickUpLocation, String chosenReturnLocation) {
		List<Bil> availibleCars = BilutleieSelskap.getUtleiekontorer()
				.stream()
				.filter(kontor -> kontor.getLokasjon() == chosenPickUpLocation)
				.toList()
				.get(0)
				.getBiler()
				.stream()
				.filter(bil -> bil.erLedigForDatoer(pickupDate, returnDate))
				.toList();
		if (availibleCars.size() == 0) {
		    JOptionPane.showMessageDialog(null, "Ingen tilgjengelige biler for valgt periode ved " + chosenPickUpLocation, "Ingen biler", JOptionPane.INFORMATION_MESSAGE);
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
            double carPrice = getCaluclatedPriceForCar(bil.getBilKategori(), pickupDate, returnDate, chosenPickUpLocation, chosenReturnLocation);
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
	
	
	private static Kunde getCustomerInfoForReservation() {
	    JPanel panel = new JPanel();
	    BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
	    panel.setLayout(boxLayout);
	    JTextField forNavnField = new JTextField(20);
	    JTextField etterNavnField = new JTextField(20);
	    JTextField gateadresseField = new JTextField(20);
	    JTextField postNmrField = new JTextField(20);
	    JTextField poststedField = new JTextField(20);
	    JTextField tlfNmrField = new JTextField(20);
	    panel.add(new JLabel("Fornavn:"));
	    panel.add(forNavnField);
	    panel.add(new JLabel("Etternavn:"));
	    panel.add(etterNavnField);
	    panel.add(new JLabel("Gateadresse:"));
	    panel.add(gateadresseField);
	    panel.add(new JLabel("Postnummer:"));
	    panel.add(postNmrField);
	    panel.add(new JLabel("Poststed:"));
	    panel.add(poststedField);
	    panel.add(new JLabel("Telefonnummer:"));
	    panel.add(tlfNmrField);
	    int option = JOptionPane.showConfirmDialog(null, panel, "Vennligst legg inn kundedata for å bekrefte reservasjon.", 
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    if (option == JOptionPane.OK_OPTION) {
	        String fornavn = forNavnField.getText();
	        String etternavn = etterNavnField.getText();
	        String gateadresse = gateadresseField.getText();
	        String postnummer = postNmrField.getText();
	        String poststed = poststedField.getText();
	        String tlfNmr = tlfNmrField.getText();
	        Adresse adresse = new Adresse(gateadresse, postnummer, poststed);
	        Kunde kunde = new Kunde(fornavn, etternavn, adresse, tlfNmr);
	        return kunde;
	    }
	    return null; 
	}
		
	private static double getCaluclatedPriceForCar(
			BilKategori bilkategori, 
			LocalDateTime henteDato, 
			LocalDateTime returDato, 
			String henteLokasjon, 
			String returLokasjon) 
		{
	    long days = ChronoUnit.DAYS.between(henteDato, returDato);
	    int dagspris = PrisKonfigurasjon.getDagspris(bilkategori);
	    int totalPris = (int) (days * dagspris);
	    if (!henteLokasjon.equals(returLokasjon)) {
	        int gebyr = PrisKonfigurasjon.getGebyr(henteLokasjon, returLokasjon);
	        totalPris += gebyr;
	    }
	    return totalPris;
	}
    
	
	private static void confirmReservation(
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
		Utleiekontor correspondingUtleiekontor = BilutleieSelskap.getUtleiekontorer().stream().filter(kontor -> kontor.getLokasjon() == kontorHent).toList().get(0);
		correspondingUtleiekontor.addReservasjon(nyReservasjon);
		System.out.println(nyReservasjon);
		System.out.println(bil);
		System.out.println(correspondingUtleiekontor);
		BilutleieSelskap.startApp();
	}
}
