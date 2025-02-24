package bilutleieapp.helpers;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import bilutleieapp.helpers.UIHelper;


public class UIHelper {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
    @SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T getValueFromDropdownEnums(String message, String title, T[] options) {
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
    
    public static String getValueFromDropdown(String message, String title, String[] options) {
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
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd HH:mm");
        spinner.setEditor(editor);
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
    
    public static Map<String, String> getUserInputForFields(List<String> fieldLabels) {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        Map<String, JTextField> fieldMap = new LinkedHashMap<>();

        for (String label : fieldLabels) {
            panel.add(new JLabel(label));
            JTextField textField = new JTextField(20);
            fieldMap.put(label, textField);
            panel.add(textField);
        }

        int option = JOptionPane.showConfirmDialog(null, panel, "Vennligst legg inn informasjon.", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            Map<String, String> inputValues = new HashMap<>();
            for (Map.Entry<String, JTextField> entry : fieldMap.entrySet()) {
                inputValues.put(entry.getKey(), entry.getValue().getText());
            }
            return inputValues;
        }
        return null;
    }
    
    public static String getUserInputFromField(String displayMsg, String buttonConfirmText) {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
	    JTextField field = new JTextField();
	    Object[] message = {
	    		displayMsg,
	    		field
	    };
	    int option = JOptionPane.showConfirmDialog(null, message, buttonConfirmText, JOptionPane.OK_CANCEL_OPTION);
	    String value = null;
	    if (option == JOptionPane.OK_OPTION) {
	    	value = field.getText();
	        if (value.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Feltet må fylles ut!", "Feil", JOptionPane.ERROR_MESSAGE);
	        } 
	    }
	    return value;
    }
    
    public static String getUserOptionFromButtonClicked(String promptMsg, List<String> options) {
        final JDialog dialog = new JDialog((Frame) null, promptMsg, true);
        dialog.setLayout(new GridLayout(options.size(), 1)); 
        dialog.setSize(400, 200); 
        final String[] selectedOption = {null};
        for (String option : options) {
            JButton button = new JButton(option);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedOption[0] = option;
                    dialog.dispose();
                }
            });
            dialog.add(button);
        }
        dialog.setVisible(true);
        return selectedOption[0];
    }
    
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.INFORMATION_MESSAGE
        );
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
