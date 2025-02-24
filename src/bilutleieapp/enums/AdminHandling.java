package bilutleieapp.enums;

public enum AdminHandling {
	OPPRETT_UTLEIEKONTOR("Opprett nytt utleiekontor"), REGISTRER_NY_BIL("Registrer ny bil");
	
    private final String displayName;
	
	AdminHandling(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
	
}
