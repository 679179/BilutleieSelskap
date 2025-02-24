package bilutleieapp.apps;
import bilutleieapp.enums.KonsulentHandling;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.services.KonsulentService;

public class KonsulentApp implements App {
	
	private static KonsulentApp instance;
    private KonsulentService konsulentService;
    private AppManager appManager;

    private KonsulentApp() {

    }
    
    public static KonsulentApp getInstance() {
        if (instance == null) {
            instance = new KonsulentApp();
        }
        return instance;
    }

    public void setService(KonsulentService konsulentService) {
    	this.konsulentService = konsulentService;
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
    	KonsulentHandling handling = UIHelper.getValueFromDropdownEnums("Velg handling for konsulentrolle", "Konsulent valg", KonsulentHandling.values());
        if (handling == null) return;
        switch (handling) {
            case RESERVER_BIL -> appManager.getBilReservasjonApp().start();
            case REGISTRER_HENTING -> registerCarPickup();
            case REGISTRER_RETUR -> registerCarReturn();
            default -> System.out.println("Ugyldig valg, prøv igjen.");
        }
    }
	
	private void registerCarPickup() {
		konsulentService.registerCarPickup();
        appManager.startApp();
	}
	
	private void registerCarReturn() {
		konsulentService.registerCarReturn();
        appManager.startApp();
	}

}
