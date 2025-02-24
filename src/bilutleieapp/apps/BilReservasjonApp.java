package bilutleieapp.apps;
import java.time.LocalDateTime;
import bilutleieapp.entities.Bil;
import bilutleieapp.entities.Kunde;
import bilutleieapp.services.BilutleieService;
import java.util.Map;

public class BilReservasjonApp implements App {
	
	private static BilReservasjonApp instance;
    private BilutleieService bilutleieService;
    private AppManager appManager;
    
    private BilReservasjonApp() {

    }
    
    public static BilReservasjonApp getInstance() {
        if (instance == null) {
            instance = new BilReservasjonApp();
        }
        return instance;
    }
    
    public void setService(BilutleieService bilutleieService) {
    	this.bilutleieService = bilutleieService;
    }
    
    @Override
    public void setAppManager(AppManager appManager) {
    	this.appManager = appManager;
    }
    
    @Override
    public void start() {
    	
        Map<String, Object> searchParams = getSearchParams();
        
        LocalDateTime pickupDate = (LocalDateTime) searchParams.get("pickupDate");
        LocalDateTime returnDate = (LocalDateTime) searchParams.get("returnDate");
        String chosenPickUpLocation = (String) searchParams.get("chosenPickUpLocation");
        String chosenReturnLocation = (String) searchParams.get("chosenReturnLocation");
       
        Bil carPicked = getCarForReservation(pickupDate, returnDate, chosenPickUpLocation, chosenReturnLocation);
        if (carPicked == null) return;
        
        Kunde customer = getCustomerDetails();
        if (customer == null) return;
        
        double calculatedPrice = calculatePrice(carPicked, pickupDate, returnDate, chosenPickUpLocation, chosenReturnLocation);
        
        addReservation(carPicked, customer, calculatedPrice, pickupDate, returnDate, chosenPickUpLocation, chosenReturnLocation);
        
        // Restarter appen 
        appManager.startApp();
    }
    
    private Map<String, Object> getSearchParams() {
        return bilutleieService.getParamsForAvailibleCarsSearch();
    }

    private Bil getCarForReservation(LocalDateTime pickupDate, LocalDateTime returnDate, String chosenPickUpLocation, String chosenReturnLocation) {
        return bilutleieService.getCarPickedFromAvailibleCars(pickupDate, returnDate, chosenPickUpLocation, chosenReturnLocation);
    }

    private Kunde getCustomerDetails() {
        return bilutleieService.getCustomerInfoForReservation();
    }

    private double calculatePrice(Bil carPicked, LocalDateTime pickupDate, LocalDateTime returnDate, String chosenPickUpLocation, String chosenReturnLocation) {
        return bilutleieService.getCaluclatedPriceForCarCategory(
            carPicked.getBilKategori(),
            pickupDate,
            returnDate,
            chosenPickUpLocation,
            chosenReturnLocation
        );
    }

    private void addReservation(Bil carPicked, Kunde customer, double calculatedPrice, LocalDateTime pickupDate, LocalDateTime returnDate, String chosenPickUpLocation, String chosenReturnLocation) {
        bilutleieService.addReservation(
            calculatedPrice,
            carPicked,
            customer,
            chosenPickUpLocation,
            chosenReturnLocation,
            pickupDate,
            returnDate
        );
    }
}
