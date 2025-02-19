
public class UtleieKontor {

	private int kontorNmr;
	private Adresse adresse;
	private String tlfNmr;
	
	public UtleieKontor() {
	}
	
	public UtleieKontor(int kontorNmr, Adresse adresse, String tlfNmr) {
		this.kontorNmr = kontorNmr;
		this.adresse = adresse;
		this.tlfNmr = tlfNmr;
	}
	
	

	public int getKontorNmr() {
		return kontorNmr;
	}

	public void setKontorNmr(int kontorNmr) {
		this.kontorNmr = kontorNmr;
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
		return "[" + kontorNmr + ", " + adresse + ", " + tlfNmr + "]";
	}
	
	
}
