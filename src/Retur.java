import java.time.LocalDateTime;

public class Retur {

	private int kilometerstand;
	private LocalDateTime tidspunkt;
	private int regning;
	
	public Retur() {
	}
	
	public Retur(int kilometerstand, LocalDateTime tidspunkt, int regning) {
		this.kilometerstand = kilometerstand;
		this.tidspunkt = tidspunkt;
		this.regning = regning;
	}

	
	
	public int getKilometerstand() {
		return kilometerstand;
	}

	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
	}

	public LocalDateTime getTidspunkt() {
		return tidspunkt;
	}

	public void setTidspunkt(LocalDateTime tidspunkt) {
		this.tidspunkt = tidspunkt;
	}

	public int getRegning() {
		return regning;
	}

	public void setRegning(int regning) {
		this.regning = regning;
	}
	
}
