import java.time.LocalDateTime;

public class Utlaan {

	private LocalDateTime tidspunktLeige;
	private String kredittkort;
	private String registreringsNmr;
	private LocalDateTime tidspunktRetur;
	private Kunde kunde;
	private Kontor kontor;
	private Bil bil;
	
	public Utlaan() {
	}
	
	public Utlaan(LocalDateTime tidspunktLeige, String kredittkort, String registreringsNmr, LocalDateTime tidspunktRetur, Kunde kunde, Bil bil, Kontor kontor) {
		this.tidspunktLeige = tidspunktLeige;
		this.kredittkort = kredittkort;
		this.registreringsNmr = registreringsNmr;
		this.tidspunktRetur = tidspunktRetur;
		this.kunde = kunde;
		this.bil = bil;
		this.kontor = kontor;
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

	public LocalDateTime getTidspunktRetur() {
		return tidspunktRetur;
	}

	public void setTidspunktRetur(LocalDateTime tidspunktRetur) {
		this.tidspunktRetur = tidspunktRetur;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Bil getBil() {
		return bil;
	}

	public void setBil(Bil bil) {
		this.bil = bil;
	}
	
	public Kontor getUtleiekontor() {
		return kontor;
	}

	public void setUtleiekontor(Kontor kontor) {
		this.kontor = kontor;
	}

	@Override
	public String toString() {
		return "[" + tidspunktLeige + ", " + kredittkort + ", "
				+ registreringsNmr + ", " + tidspunktRetur + "]";
	}
	
}
