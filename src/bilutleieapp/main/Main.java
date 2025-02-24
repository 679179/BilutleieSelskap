package bilutleieapp.main;
import bilutleieapp.apps.AppManager;

public class Main {
    public static void main(String[] args) {
        AppInitializer initializer = new AppInitializer();
        AppManager appManager = initializer.initialize();
        appManager.startApp();
    }
}
