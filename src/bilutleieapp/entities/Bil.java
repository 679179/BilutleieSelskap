package bilutleieapp.entities;
import java.time.LocalDateTime;
import java.util.List;

import bilutleieapp.enums.BilKategori;
import bilutleieapp.enums.Color;

import java.util.ArrayList;

public class Bil {

	private String registreringsNr;
	private Utleiekontor utleieKontor;
	private String merke;
	private String modell;
	private Color farge;
	private BilKategori bilKategori;
	private List<LocalDateTime[]> reverasjonsDatoer;
	private int kmStand;
	
	public Bil(
			String registreringsNr, 
			Utleiekontor utleieKontor, 
			String merke, 
			String modell, 
			Color farge,
			BilKategori bilKategori, 
			int kmStand) 
		{
		this.registreringsNr = registreringsNr;
		this.utleieKontor = utleieKontor;
		this.merke = merke;
		this.modell = modell;
		this.farge = farge;
		this.bilKategori = bilKategori;
		this.kmStand = kmStand;
		this.reverasjonsDatoer = new ArrayList<>();
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
	public int getKmStand() {
		return kmStand;
	}
	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}
	
	public void addReservasjonsDato(LocalDateTime start, LocalDateTime end) {
		this.reverasjonsDatoer.add(new LocalDateTime[] {start, end});
	}
	
	public boolean erLedigForDatoer(LocalDateTime start, LocalDateTime end) {
        for (LocalDateTime[] reservation : reverasjonsDatoer) {
            if (start.isBefore(reservation[1]) && end.isAfter(reservation[0])) {
                return false; // overlapp, ikke ledig
            }
        }
        return true;
    }
	
	@Override
	public String toString() {
		return "Bil [registreringsNr=" + registreringsNr + ", merke=" + merke + ", modell=" + modell + ", farge="
				+ farge + "]";
	}

}
