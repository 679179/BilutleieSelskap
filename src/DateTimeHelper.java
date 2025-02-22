
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHelper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime getDateAndTimeFromUser(String prompt) {
        while (true) {
            String input = JOptionPane.showInputDialog(prompt);
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
}

