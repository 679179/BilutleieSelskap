package bilutleieapp.apps;
import bilutleieapp.enums.AdminHandling;
import bilutleieapp.helpers.UIHelper;
import bilutleieapp.services.AdminService;

public class AdminApp implements App {
	
	private static AdminApp instance;
    private AdminService adminService;
    private AppManager appManager;
 
    private AdminApp() {

    }
    
    public static AdminApp getInstance() {
        if (instance == null) {
            instance = new AdminApp();
        }
        return instance;
    }
    
	public void setService(AdminService adminService) {
		this.adminService = adminService;
		
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
    	AdminHandling handling = UIHelper.getValueFromDropdownEnums("Velg handling for adminprogram", "Admin valg", AdminHandling.values());
        switch (handling) {
	        case OPPRETT_UTLEIEKONTOR -> registerNewUtleiekontor();
	        case REGISTRER_NY_BIL -> registerNewCar();
	        default -> System.out.println("Ugyldig valg, prøv igjen.");
        }
    }
    
    
    private void registerNewUtleiekontor() {
        adminService.registerNewUtleiekontor();
        appManager.startApp();
    }
    
    private void registerNewCar() {
    	adminService.registerNewCar();
        appManager.startApp();
    }

}
