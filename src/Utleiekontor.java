import java.util.List;
import java.util.ArrayList;

public class Utleiekontor {

	private Lokasjon lokasjon;
	private int kontorNr;
	private Adresse adresse;
	private String tlfNr;
	private List<Bil> biler;
	private List<Reservasjon> reservasjoner;

	
	public Utleiekontor(Lokasjon lokasjon, int kontorNr, Adresse adresse, String tlfNr) {
		this.lokasjon = lokasjon;
		this.kontorNr = kontorNr;
		this.adresse = adresse;
		this.tlfNr = tlfNr;
		this.biler = new ArrayList<Bil>();
		this.reservasjoner = new ArrayList<Reservasjon>();
	}

	public Lokasjon getLokasjon() {
		return lokasjon;
	}
	public void setLokasjon(Lokasjon lokasjon) {
		this.lokasjon = lokasjon;
	}
	public int getKontorNr() {
		return kontorNr;
	}
	public void setKontorNr(int kontorNr) {
		this.kontorNr = kontorNr;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public String getTlfNr() {
		return tlfNr;
	}
	public void setTlfNr(String tlfNr) {
		this.tlfNr = tlfNr;
	}
	
	public List<Bil> getBiler() {
		return biler;
	}
	
	public List<Reservasjon> getReservasjoner() {
		return reservasjoner;
	}
	
	public void addBil(Bil bil) {
		biler.add(bil);
	}
	
	public void addReservasjon(Reservasjon reservasjon) {
		reservasjoner.add(reservasjon);
	}
	
	public void setReservasjoner(List<Reservasjon> reservasjoner) {
		this.reservasjoner = reservasjoner;
	}

	@Override
	public String toString() {
	    return "Utleiekontor:\n" +
	           "  Lokasjon: " + lokasjon + "\n" +
	           "  KontorNr: " + kontorNr + "\n" +
	           "  Adresse: " + adresse + "\n" +
	           "  TelefonNr: " + tlfNr + "\n" +
	           "  Biler: " + biler + "\n" +
	           "  Reservasjoner: " + reservasjoner + "\n";
	}

	
	
}
