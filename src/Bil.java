
public class Bil {

	private String registreringsNr;
	private Utleiekontor utleieKontor;
	private String merke;
	private String modell;
	private Color farge;
	private BilKategori bilKategori;
	private boolean ledig;
	private int kmStand;
	
	public Bil(
			String registreringsNr, 
			Utleiekontor utleieKontor, 
			String merke, 
			String modell, 
			Color farge,
			BilKategori bilKategori, 
			boolean ledig, 
			int kmStand) 
		{
		this.registreringsNr = registreringsNr;
		this.utleieKontor = utleieKontor;
		this.merke = merke;
		this.modell = modell;
		this.farge = farge;
		this.bilKategori = bilKategori;
		this.ledig = ledig;
		this.kmStand = kmStand;
	}
	
	public String getRegistreringsNr() {
		return registreringsNr;
	}
	public void setRegistreringsNr(String registreringsNr) {
		this.registreringsNr = registreringsNr;
	}
	public Utleiekontor getUtleieKontor() {
		return utleieKontor;
	}
	public void setUtleieKontor(Utleiekontor utleieKontor) {
		this.utleieKontor = utleieKontor;
	}
	public String getMerke() {
		return merke;
	}
	public void setMerke(String merke) {
		this.merke = merke;
	}
	public String getModell() {
		return modell;
	}
	public void setModell(String modell) {
		this.modell = modell;
	}
	public Color getFarge() {
		return farge;
	}
	public void setFarge(Color farge) {
		this.farge = farge;
	}
	public BilKategori getBilKategori() {
		return bilKategori;
	}
	public void setBilKategori(BilKategori bilKategori) {
		this.bilKategori = bilKategori;
	}
	public boolean isLedig() {
		return ledig;
	}
	public void setLedig(boolean ledig) {
		this.ledig = ledig;
	}
	public int getKmStand() {
		return kmStand;
	}
	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}
	
	@Override
	public String toString() {
		return "Bil [registreringsNr=" + registreringsNr + ", merke=" + merke + ", modell=" + modell + ", farge="
				+ farge + "]";
	}

}
