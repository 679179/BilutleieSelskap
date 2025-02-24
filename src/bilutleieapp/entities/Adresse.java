package bilutleieapp.entities;

public class Adresse {

	private String gateadresse;
	private String postNmr; 
	private String poststed;
	
	public Adresse() {
	}
	
	public Adresse(String gateadresse, String postNmr, String poststed) {
		this.gateadresse = gateadresse;
		this.postNmr = postNmr;
		this.poststed = poststed;
	}

	public String getGateadresse() {
		return gateadresse;
	}

	public void setGateadresse(String gateadresse) {
		this.gateadresse = gateadresse;
	}

	public String getPostNmr() {
		return postNmr;
	}

	public void setPostNmr(String postNmr) {
		this.postNmr = postNmr;
	}

	public String getPoststed() {
		return poststed;
	}

	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	@Override
	public String toString() {
		return gateadresse + ", " + postNmr + ", " + poststed;
	}
	
}
