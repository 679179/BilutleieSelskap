package bilutleieapp.main;
import bilutleieapp.apps.*;
import bilutleieapp.entities.*;
import bilutleieapp.repository.Repository;
import bilutleieapp.services.BilutleieService;
import bilutleieapp.services.KonsulentService;
import bilutleieapp.services.AdminService;

public class AppInitializer {
    
    public AppManager initialize() {
    	
        BilutleieSelskap bilutleieSelskap = new BilutleieSelskap(
                "Goated bilutleie", new Adresse("Skaratunet 13", "6800", "Forde"), "12345678"
        );

        Repository repo = Repository.getInstance();
        repo.setBilutleieselskap(bilutleieSelskap);

        // service singletons 
        BilutleieService bilutleieService = BilutleieService.getInstance();
        KonsulentService konsulentService = KonsulentService.getInstance();
        AdminService adminService = AdminService.getInstance();
         
        
        // inject repo to all services 
        bilutleieService.setRepository(repo);
        konsulentService.setRepository(repo);
        adminService.setRepository(repo);
        
        // app singletons 
        BilReservasjonApp bilReservasjonsApp = BilReservasjonApp.getInstance();
        KonsulentApp konsulentApp = KonsulentApp.getInstance();
        AdminApp adminApp = AdminApp.getInstance();
        
        // app manager singleton
        AppManager appManager = AppManager.getInstance();
        
        // inject services into corresponding app
        bilReservasjonsApp.setService(bilutleieService);
        konsulentApp.setService(konsulentService);
        adminApp.setService(adminService);
        
        // inject app manager into all apps
        bilReservasjonsApp.setAppManager(appManager);
        konsulentApp.setAppManager(appManager);
        adminApp.setAppManager(appManager);
        
        // Inject apps into app manager
        appManager.setBilReservasjonApp(bilReservasjonsApp);
        appManager.setKonsulentApp(konsulentApp);
        appManager.setAdminApp(adminApp);
       
        // Generate dummy data
        generateDummyData(repo, bilutleieService);
        
        // System.out.println("REPO UTLEIEKONTORER ETTER DUMMY \n: " + repo.getAllUtleiekontorer());

        return appManager;
    }

    private void generateDummyData(Repository repo, BilutleieService bilutleieService) {
        DummyDataGenerator.addGeneratedKontorer(repo.getAllUtleiekontorer());
        DummyDataGenerator.addGeneratedCarsToKontorer(repo.getAllUtleiekontorer());
        DummyDataGenerator.addGeneratedReservations(repo.getAllUtleiekontorer(), repo.getAllKunder(), bilutleieService);
        repo.setLokasjoner(repo.getAllUtleiekontorer().stream().map(Utleiekontor::getLokasjon).toList());
    }
}
