import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Utils {
	
	private static List<String> lokasjoner = BilutleieSelskap.getLokasjoner();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
    public static String getExistingLocationFromUser() {
        String[] options = lokasjoner.toArray(new String[0]);  // Convert List to Array
        String selectedLocation = (String) JOptionPane.showInputDialog(
                null,
                "Vel tilhørende lokasjon for ditt kontor",
                "Lokasjon Valg",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        return selectedLocation;
    }
    
	public static String getLocationFromUser(String promptMsg) {
        final JDialog dialog = new JDialog((Frame) null, promptMsg, true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(400, 200);

        final String[] selectedLocation = {null};
       

        for (String lokasjon : BilutleieSelskap.getLokasjoner()) {
            JButton button = new JButton(lokasjon);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedLocation[0] = lokasjon;
                    dialog.dispose();
                }
            });
            dialog.add(button);
        }

        dialog.setVisible(true);
        return selectedLocation[0];
	}

    public static LocalDateTime getDateAndTimeFromUser(String promptMsg) {
        while (true) {
            String input = JOptionPane.showInputDialog(promptMsg);
            if (input != null) { 
                try {
                    return LocalDateTime.parse(input, formatter);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(null, "Vennligst oppgi i format YYYY-MM-DD HH:MM.");
                }
            } else {
                return null; 
            }
        }
    }
    
    public static Utleiekontor findUtleiekontorByLocation(String lokasjon) {
		Utleiekontor kontor = BilutleieSelskap.getUtleiekontorer().stream()
		        .filter(k -> k.getLokasjon().toUpperCase().equals(lokasjon.toUpperCase()))
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("Utleiekontor med lokasjon " + lokasjon + " ikke funnet"));
		return kontor;
    }
	
}
