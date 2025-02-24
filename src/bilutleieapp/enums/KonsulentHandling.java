package bilutleieapp.enums;

public enum KonsulentHandling {
    RESERVER_BIL("Reserver bil for kunde"),
    REGISTRER_HENTING("Registrer henting av bil"),
    REGISTRER_RETUR("Registrer retur av bil");

    private final String displayName;

    KonsulentHandling(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
