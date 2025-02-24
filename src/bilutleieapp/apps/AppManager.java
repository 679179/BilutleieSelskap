package bilutleieapp.apps;
import javax.swing.JOptionPane;

import bilutleieapp.enums.BrukerRolle;
import bilutleieapp.helpers.UIHelper;

public class AppManager {
	
    private static AppManager instance;
    private BilReservasjonApp bilReservasjonApp;
    private KonsulentApp konsulentApp;
    private AdminApp adminApp;

    private AppManager() {

    }
    
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }
    
	public BilReservasjonApp getBilReservasjonApp() {
		return bilReservasjonApp;
	}

	public KonsulentApp getKonsulentApp() {
		return konsulentApp;
	}

	public AdminApp getAdminApp() {
		return adminApp;
	}

	public void setBilReservasjonApp(BilReservasjonApp bilReservasjonApp) {
		this.bilReservasjonApp = bilReservasjonApp;
	}
    
	public void setKonsulentApp(KonsulentApp konsulentApp) {
		this.konsulentApp = konsulentApp;
	}

	public void setAdminApp(AdminApp adminApp) {
		this.adminApp = adminApp;
	}

	public void startApp() {
		redirectToApp();
    }
	
	public void redirectToApp() {
		BrukerRolle selectedRole = UIHelper.chooseUserActionFromEnumOptions("Velg brukersystem", "Bruker Valg", BrukerRolle.values());
        if (selectedRole == null) return;
        switch (selectedRole) {
            case KUNDE -> bilReservasjonApp.start();
            case KONSULENT -> konsulentApp.start();
            case ADMIN -> adminApp.start();
            default -> System.out.println("Ugyldig valg, prøv igjen.");
        }
		
	}
}