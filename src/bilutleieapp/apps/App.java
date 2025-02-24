package bilutleieapp.apps;

import bilutleieapp.repository.Repository;
import bilutleieapp.services.BilutleieService;

public interface App {
    void setBilutleieService(BilutleieService service);
    void setRepository(Repository repository);
    void setAppManager(AppManager appManager);
    void start();
}
