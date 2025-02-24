package bilutleieapp.entities;
public class BilutleieSelskap {
	
    private String navn;
    private Adresse firmaAdresse;
    private String tlfNmr;
    
	public BilutleieSelskap(String navn, Adresse firmaAdresse, String tlfNmr) {
		this.navn = navn;
		this.firmaAdresse = firmaAdresse;
		this.tlfNmr = tlfNmr;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Adresse getFirmaAdresse() {
		return firmaAdresse;
	}

	public void setFirmaAdresse(Adresse firmaAdresse) {
		this.firmaAdresse = firmaAdresse;
	}

	public String getTlfNmr() {
		return tlfNmr;
	}

	public void setTlfNmr(String tlfNmr) {
		this.tlfNmr = tlfNmr;
	}   
}
