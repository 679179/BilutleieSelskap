import java.time.LocalDateTime;

public class Utlaan {

	private LocalDateTime tidspunktLeige;
	private String kredittkort;
	private String registreringsNmr;
	private int kilometerstand;
	private LocalDateTime tidspunktRetur;
	
	public Utlaan() {
	}
	
	public Utlaan(LocalDateTime tidspunktLeige, String kredittkort, String registreringsNmr, int kilometerstand, LocalDateTime tidspunktRetur) {
		this.tidspunktLeige = tidspunktLeige;
		this.kredittkort = kredittkort;
		this.registreringsNmr = registreringsNmr;
		this.kilometerstand = kilometerstand;
		this.tidspunktRetur = tidspunktRetur;
	}

	public LocalDateTime getTidspunktLeige() {
		return tidspunktLeige;
	}

	public void setTidspunktLeige(LocalDateTime tidspunktLeige) {
		this.tidspunktLeige = tidspunktLeige;
	}

	public String getKredittkort() {
		return kredittkort;
	}

	public void setKredittkort(String kredittkort) {
		this.kredittkort = kredittkort;
	}

	public String getRegistreringsNmr() {
		return registreringsNmr;
	}

	public void setRegistreringsNmr(String registreringsNmr) {
		this.registreringsNmr = registreringsNmr;
	}
	
	public int getKilometerstand() {
		return kilometerstand;
	}
	
	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
	}

	public LocalDateTime getTidspunktRetur() {
		return tidspunktRetur;
	}

	public void setTidspunktRetur(LocalDateTime tidspunktRetur) {
		this.tidspunktRetur = tidspunktRetur;
	}

	@Override
	public String toString() {
		return "[" + tidspunktLeige + ", " + kredittkort + ", "
				+ registreringsNmr + ", " + kilometerstand + ", " + tidspunktRetur + "]";
	}
	
}
