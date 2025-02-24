package bilutleieapp.helpers;
import javax.swing.*;
// import javafx.scene.control.DatePicker;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UIHelper {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
    @SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T chooseUserActionFromEnumOptions(String message, String title, T[] options) {
        return (T) JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options.length > 0 ? options[0] : null
        );
    }
    
    public static String chooseUserActionFromOptions(String message, String title, String[] options) {
        return (String) JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options.length > 0 ? options[0] : null
        );
    }
    
    public static LocalDateTime getDateFromUser(String promptMsg) {
    	
    	/*
    	
    	 DatePicker datePicker = new DatePicker();
    	 datePicker.setOnAction(e -> {
    	     LocalDate date = datePicker.getValue();
    	     System.err.println("Selected date: " + date);
    	 });
    
        */
    	
    	
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd HH:mm");
        spinner.setEditor(editor);

        // Display the dialog
        JOptionPane.showMessageDialog(
                null,
                new Object[]{promptMsg, spinner},
                "Select Date and Time",
                JOptionPane.QUESTION_MESSAGE
        );

        Date selectedDate = (Date) spinner.getValue();
        return convertToLocalDateTime(selectedDate);
        
        
    }

    private static LocalDateTime convertToLocalDateTime(Date date) {
        return LocalDateTime.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date), formatter);
    }
    
    public static void showError(String errorMsg) {
        JOptionPane.showMessageDialog(
                null,
                errorMsg,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    
}
