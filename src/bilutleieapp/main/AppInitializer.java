package bilutleieapp.main;

import bilutleieapp.apps.*;
import bilutleieapp.entities.*;
import bilutleieapp.repository.Repository;
import bilutleieapp.services.BilutleieService;
import java.util.List;

public class AppInitializer {
    
    public AppManager initialize() {
        // Initialize core dependencies
        BilutleieSelskap bilutleieSelskap = new BilutleieSelskap(
                "Goated bilutleie", new Adresse("Skaratunet 13", "6800", "Forde"), "12345678"
        );

        Repository repo = Repository.getInstance();
        repo.setBilutleieselskap(bilutleieSelskap);

        BilutleieService bilutleieService = BilutleieService.getInstance();
        bilutleieService.setRepository(repo);

        // Initialize apps
        List<App> apps = List.of(
            BilReservasjonApp.getInstance(),
            KonsulentApp.getInstance(),
            AdminApp.getInstance()
        );

        // Initialize AppManager
        AppManager appManager = AppManager.getInstance();
        
        // Inject dependencies into apps
        for (App app : apps) {
            app.setBilutleieService(bilutleieService);
            app.setRepository(repo);
            app.setAppManager(appManager);
        }

        // Inject apps into AppManager
        appManager.setBilReservasjonApp((BilReservasjonApp) apps.get(0));
        appManager.setKonsulentApp((KonsulentApp) apps.get(1));
        appManager.setAdminApp((AdminApp) apps.get(2));

        // Generate dummy data
        generateDummyData(repo, bilutleieService);

        return appManager;
    }

    private void generateDummyData(Repository repo, BilutleieService bilutleieService) {
        DummyDataGenerator.addGeneratedKontorer(repo.getAllUtleiekontorer());
        DummyDataGenerator.addGeneratedCarsToKontorer(repo.getAllUtleiekontorer());
        DummyDataGenerator.addGeneratedReservations(repo.getAllUtleiekontorer(), repo.getAllKunder(), bilutleieService);
        repo.setLokasjoner(repo.getAllUtleiekontorer().stream().map(Utleiekontor::getLokasjon).toList());
    }
}
