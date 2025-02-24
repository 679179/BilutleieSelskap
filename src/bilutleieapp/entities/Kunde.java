package bilutleieapp.entities;

public class Kunde {

	private String fornavn;
	private String etternavn;
	private Adresse adresse;
	private String tlfNmr;
	private String kredittkort;
	
	public Kunde(String fornavn, String etternavn, Adresse adresse, String tlfNmr) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.adresse = adresse;
		this.tlfNmr = tlfNmr;
	}
	
    public Kunde(String fornavn, String etternavn, Adresse adresse, String tlfNmr, String kredittkort) {
        this(fornavn, etternavn, adresse, tlfNmr);
        this.kredittkort = kredittkort;
    }

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
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
	
	public String getKredittkort() {
		return kredittkort;
	}

	public void setKredittkort(String kredittkort) {
		this.kredittkort = kredittkort;
	}

	@Override
	public String toString() {
		return "Kunde [fornavn=" + fornavn + ", etternavn=" + etternavn + "]";
	}
	
}
