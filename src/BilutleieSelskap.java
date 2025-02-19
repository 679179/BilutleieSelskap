import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BilutleieSelskap {

	private String navn;
	private Adresse firmaAdresse;
	private String tlfNmr;
	
	
	public static void main(String[] args) {
		
		Adresse kontor1Adresse = new Adresse("Vieåsen", "6812", "Førde");
		UtleieKontor kontor1 = new UtleieKontor(1, kontor1Adresse, "70262028");
		
		Bil bil1 = new Bil("EL902018", "VW", Colour.BLACK, Utleiegruppe.B, true);
		
		
		Adresse kunde1Adresse = new Adresse("Tegane", "6200", "Stranda");
		Kunde kunde1 = new Kunde("Sander", kunde1Adresse, "90521880");
		
		Utlaan utlaan1 = new Utlaan((getLocalTime()), "4313130075849976", "EL902018", 35000, getLocalTime());
		
		System.out.println(kunde1.toString() + "\n" + kontor1.toString() + "\n" + bil1.toString() + utlaan1.toString());
		
	}
	
	public BilutleieSelskap() {
	}
	
	public BilutleieSelskap(String navn, Adresse firmaAdresse, String tlfNmr) {
		this.navn = navn;
		this.firmaAdresse = firmaAdresse;
		this.tlfNmr = tlfNmr;
	}
	
	public static LocalDateTime getLocalTime() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");
		LocalDateTime tidspunkt = LocalDateTime.now();
		
		return tidspunkt;
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
