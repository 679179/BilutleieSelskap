
public class Bil {

	private String registreringsNmr;
	private String modell;
	private Colour farge;
	private Utleiegruppe gruppe;
	private boolean ledig;
	
	public Bil() {
	}
	
	public Bil(String registreringsNmr, String modell, Colour farge, Utleiegruppe gruppe, boolean ledig) {
		this.registreringsNmr = registreringsNmr;
		this.modell = modell;
		this.farge = farge;
		this.gruppe = gruppe;
		this.ledig = ledig;
	}

	
	
	public String getRegistreringsNmr() {
		return registreringsNmr;
	}

	public void setRegistreringsNmr(String registreringsNmr) {
		this.registreringsNmr = registreringsNmr;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public Colour getFarge() {
		return farge;
	}

	public void setFarge(Colour farge) {
		this.farge = farge;
	}

	public Utleiegruppe getGruppe() {
		return gruppe;
	}

	public void setGruppe(Utleiegruppe gruppe) {
		this.gruppe = gruppe;
	}

	public boolean isLedig() {
		return ledig;
	}

	public void setLedig(boolean ledig) {
		this.ledig = ledig;
	}

	@Override
	public String toString() {
		return "[" + registreringsNmr + ", " + modell + ", " + farge + ", "
				+ gruppe + ", " + ledig + "]";
	}
	
}
