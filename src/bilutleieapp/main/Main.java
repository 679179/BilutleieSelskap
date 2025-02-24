package bilutleieapp.main;
import bilutleieapp.apps.AppManager;

public class Main {
    public static void main(String[] args) {
        // Initialize app dependencies
        AppInitializer initializer = new AppInitializer();
        AppManager appManager = initializer.initialize();

        // Start the application
        appManager.startApp();
    }
}
