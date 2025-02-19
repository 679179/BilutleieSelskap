
public class Kunde {

	private String navn;
	private Adresse adresse;
	private String tlfNmr;
	
	public Kunde() {
	}
	
	public Kunde(String navn, Adresse adresse, String tlfNmr) {
		this.navn = navn;
		this.adresse = adresse;
		this.tlfNmr = tlfNmr;
	}
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getTlfNmr() {
		return tlfNmr;
	}

	public void setTlfNmr(String tlfNmr) {
		this.tlfNmr = tlfNmr;
	}

	@Override
	public String toString() {
		return "[" +  navn + ", " + adresse + ", " + tlfNmr + "]";
	}
	
}
