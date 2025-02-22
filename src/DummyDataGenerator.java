import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class DummyDataGenerator {
	
    public static void addGeneratedKontorer(List<Utleiekontor> utleiekontorer) {
        utleiekontorer.add(new Utleiekontor(Lokasjon.FORDE, 1, new Adresse("Hafstadvegen 23", "6800", "Førde"), "12345678"));
        utleiekontorer.add(new Utleiekontor(Lokasjon.OSLO, 2, new Adresse("Karl Johans gate 15", "0154", "Oslo"), "23456789"));
        utleiekontorer.add(new Utleiekontor(Lokasjon.TRONDHEIM, 3, new Adresse("Munkegata 34", "7011", "Trondheim"), "34567890"));
        utleiekontorer.add(new Utleiekontor(Lokasjon.BERGEN, 4, new Adresse("Bryggen 47", "5003", "Bergen"), "45678901"));
        utleiekontorer.add(new Utleiekontor(Lokasjon.KRISTIANSAND, 5, new Adresse("Markens gate 35", "4612", "Kristiansand"), "56789012"));
    }
    
	public static void addGeneratedCarsToKontorer( List<Utleiekontor> utleiekontorer) {
		
	    Bil[] biler = {
	            new Bil("AB1234", null, "Toyota", "Corolla", Color.RED, BilKategori.LITEN, false, 50000),
	            new Bil("CD5678", null, "BMW", "i3", Color.BLUE, BilKategori.MELLOMSTOR, false, 75000),
	            new Bil("EF9012", null, "Tesla", "Model S", Color.BLACK, BilKategori.STASJONSVOGN, false, 20000),
	            new Bil("GH3456", null, "Volkswagen", "Golf", Color.SILVER, BilKategori.LITEN, false, 60000),
	            new Bil("IJ7890", null, "Ford", "Focus", Color.GRAY, BilKategori.MELLOMSTOR, false, 85000),
	            
	            new Bil("KL1234", null, "Nissan", "Altima", Color.WHITE, BilKategori.STOR, false, 30000),
	            new Bil("MN5678", null, "Hyundai", "Santa Fe", Color.BLACK, BilKategori.STASJONSVOGN, false, 45000),
	            new Bil("OP9012", null, "Audi", "A4", Color.BLUE, BilKategori.MELLOMSTOR, false, 40000),
	            new Bil("QR3456", null, "Mazda", "CX-5", Color.RED, BilKategori.STOR, false, 52000),
	            new Bil("ST7890", null, "Chevrolet", "Impala", Color.SILVER, BilKategori.STOR, false, 90000),
	            
	            new Bil("UV1234", null, "Honda", "Civic", Color.GREEN, BilKategori.LITEN, false, 15000),
	            new Bil("WX5678", null, "Mercedes", "C-Class", Color.ORANGE, BilKategori.STASJONSVOGN, false, 7000),
	            new Bil("YZ9012", null, "Kia", "Sorento", Color.PURPLE, BilKategori.STASJONSVOGN, false, 22000),
	            new Bil("AB3456", null, "Subaru", "Outback", Color.GRAY, BilKategori.MELLOMSTOR, false, 67000),
	            new Bil("CD7890", null, "Jeep", "Cherokee", Color.YELLOW, BilKategori.LITEN, false, 49000),
	            
	            new Bil("EF1234", null, "Volvo", "XC90", Color.RED, BilKategori.MELLOMSTOR, false, 34000),
	            new Bil("GH5678", null, "Jaguar", "XE", Color.BLUE, BilKategori.STASJONSVOGN, false, 56000),
	            new Bil("IJ9012", null, "Lexus", "RX", Color.BLACK, BilKategori.MELLOMSTOR, false, 29000),
	            new Bil("KL3456", null, "Porsche", "Macan", Color.SILVER, BilKategori.STASJONSVOGN, false, 47000),
	            new Bil("MN7890", null, "Land Rover", "Range Rover", Color.GRAY, BilKategori.STASJONSVOGN, false, 110000)
	        };
		    
        for (int i = 0; i < biler.length; i++) {
        	int kontorIndex = i % 5;
            biler[i].setUtleieKontor(utleiekontorer.get(kontorIndex));
            utleiekontorer.get(kontorIndex).addBil(biler[i]);
        }
	}
    
    
    
	public static void addGeneratedReservations(List<Utleiekontor> utleiekontorer, List<Kunde> kunder) {
		
        Random random = new Random();
    
        String[] fornavn = {"Ola", "Kari", "Per", "Lise", "Anna"};
        String[] etternavn = {"Nordmann", "Hansen", "Johansen", "Olsen", "Larsen"};
        String[] gateadresser = {"Hovedgata 10", "Bekkveien 3", "Fjellveien 15", "Strandgata 22", "Parkveien 5"};
        String[] poststeder = {"Oslo", "Bergen", "Trondheim", "Førde", "Kristiansand"};
        String[] postnummer = {"0101", "5000", "7000", "6800", "4612"};
        
        for (int i = 0; i < utleiekontorer.size(); i++) {
            // Generer en tilfeldig kunde
            String fnavn = fornavn[random.nextInt(fornavn.length)];
            String enavn = etternavn[random.nextInt(etternavn.length)];
            String gateadresse = gateadresser[random.nextInt(gateadresser.length)];
            String poststed = poststeder[random.nextInt(poststeder.length)];
            String postNr = postnummer[random.nextInt(postnummer.length)];
            Adresse adresse = new Adresse(gateadresse, postNr, poststed);
            Kunde kunde = new Kunde(fnavn, enavn, adresse, "12345678"); // Telefonnummer som placeholder
            kunder.add(kunde);

            // Generer tilfeldige hentetidspunkt og returpunkt (± 2 uker)
            LocalDateTime datoHent = LocalDateTime.now().minusDays(random.nextInt(14) + 1);
            LocalDateTime datoRetur = datoHent.plusDays(random.nextInt(14) + 1);
            
            // Velg tilfeldige kontorer for henting og retur
            Utleiekontor kontorHent = utleiekontorer.get(i % utleiekontorer.size());
            Utleiekontor kontorRetur = utleiekontorer.get(random.nextInt(utleiekontorer.size()));

            // Velg en tilfeldig bil fra kontoret
            Bil bil = kontorHent.getBiler().get(random.nextInt(kontorHent.getBiler().size()));

            // Beregn pris (f.eks. dagspris + gebyr mellom kontorer)
            int dager = (int) ChronoUnit.DAYS.between(datoHent, datoRetur);
            double dagspris = PrisKonfigurasjon.getDagspris(bil.getBilKategori());
            double totalPris = dager * dagspris;
            
            Lokasjon kontorHentLokasjon = kontorHent.getLokasjon();
            Lokasjon kontorReturLokasjon = kontorHent.getLokasjon();
            
            // Opprett reservasjonen
            Reservasjon reservasjon = new Reservasjon(totalPris, bil, kunde, kontorHentLokasjon, kontorReturLokasjon, datoHent, datoRetur);

            // Legg til reservasjonen
            kontorHent.addReservasjon(reservasjon);
        }
		
	}  

}
