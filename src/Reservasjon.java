import java.time.LocalDateTime;

public class Reservasjon {
	
	private double pris;
	private Bil bil;
	private Kunde kunde;
	private Utleiekontor kontorHent;
	private Utleiekontor kontorRetur;
	private LocalDateTime datoHent;
	private LocalDateTime datoRetur;
	private LocalDateTime datoFaktiskRetur;
	private double skydligBelop;
	
	public Reservasjon(
			double pris, 
			Bil bil, 
			Kunde kunde, 
			Utleiekontor kontorHent, 
			Utleiekontor kontorRetur,
			LocalDateTime datoHent, 
			LocalDateTime datoRetur
		) {

		this.pris = pris;
		this.bil = bil;
		this.kunde = kunde;
		this.kontorHent = kontorHent;
		this.kontorRetur = kontorRetur;
		this.datoHent = datoHent;
		this.datoRetur = datoRetur;
	}
	
	public double getPris() {
		return pris;
	}
	public void setPris(double pris) {
		this.pris = pris;
	}
	public Bil getBil() {
		return bil;
	}
	public void setBil(Bil bil) {
		this.bil = bil;
	}
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	public Utleiekontor getKontorHent() {
		return kontorHent;
	}
	public void setKontorHent(Utleiekontor kontorHent) {
		this.kontorHent = kontorHent;
	}
	public Utleiekontor getKontorRetur() {
		return kontorRetur;
	}
	public void setKontorRetur(Utleiekontor kontorRetur) {
		this.kontorRetur = kontorRetur;
	}
	public LocalDateTime getDatoHent() {
		return datoHent;
	}
	public void setDatoHent(LocalDateTime datoHent) {
		this.datoHent = datoHent;
	}
	public LocalDateTime getDatoRetur() {
		return datoRetur;
	}
	public void setDatoRetur(LocalDateTime datoRetur) {
		this.datoRetur = datoRetur;
	}
	public LocalDateTime getDatoFaktiskRetur() {
		return datoFaktiskRetur;
	}
	public void setDatoFaktiskRetur(LocalDateTime datoFaktiskRetur) {
		this.datoFaktiskRetur = datoFaktiskRetur;
	}
	public double getSkydligBelop() {
		return skydligBelop;
	}
	public void setSkydligBelop(double skydligBelop) {
		this.skydligBelop = skydligBelop;
	}
	
	
	@Override
	public String toString() {
	    return "Reservasjon:\n" +
	           "  Pris: " + pris + "\n" +
	           "  Bil: " + (bil != null ? bil.getRegistreringsNr() : "null") + "\n" +
	           "  Kunde: " + (kunde != null ? kunde.getFornavn() + " " + kunde.getEtternavn() : "null") + "\n" +
	           "  Dato Hent: " + datoHent + "\n" +
	           "  Dato Retur: " + datoRetur + "\n" +
	           "  Dato Faktisk Retur: " + datoFaktiskRetur + "\n" +
	           "  Skyldig Beløp: " + skydligBelop + "\n";
	}
	
	

}
