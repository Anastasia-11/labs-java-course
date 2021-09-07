import lab5.Exponential;
import lab5.Linear;
import lab5.Series;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JTextField sumField;
    private JTextField firstElementField;
    private JTextField numberOfElementsField;
    private JTextField coefficientField;
    private JButton buttonUpdate;
    private JTextField fileNameField;
    private JButton buttonToFile;
    private JRadioButton exponentialRadioButton;
    private JRadioButton linearRadioButton;
    private JTextPane seriesField;

    private Series series;

    public Dialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonClose);

        firstElementField.setText("1");
        numberOfElementsField.setText("5");
        coefficientField.setText("2");
        series = new Linear(1, 5, 2);

        buttonClose.addActionListener(e -> onClose());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        buttonUpdate.addActionListener(e -> {
            if(dataIsCorrect()) {
                UpdateSeriesType();
                UpdateSum();
                UpdateSeries();
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect input data!");
            }
        });

        buttonToFile.addActionListener(e -> {
            try {
                if(fileNameIsCorrect()){
                    series.toFile(fileNameField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect file name!");
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "There was an error while writing data to the file!");
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onClose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private boolean fileNameIsCorrect() {
        String fileName = fileNameField.getText();
        Pattern pattern = Pattern.compile(".+.txt$");
        Matcher matcher = pattern.matcher(fileName);
        return matcher.matches();
    }

    private void UpdateSeriesType() {
        double first = Double.parseDouble(firstElementField.getText());
        int N = Integer.parseInt(numberOfElementsField.getText());
        double coefficient = Double.parseDouble(coefficientField.getText());
        if (linearRadioButton.isSelected()) {
            series = new Linear(first, N, coefficient);
        }
        if (exponentialRadioButton.isSelected()) {
            series = new Exponential(first, N, coefficient);
        }
    }

    private boolean dataIsCorrect() {
        try {
            Double.parseDouble(firstElementField.getText());
            int N = Integer.parseInt(numberOfElementsField.getText());
            if (N <= 0)
                return false;
            Double.parseDouble(coefficientField.getText());
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    private void onClose() {
        dispose();
    }

    private void UpdateSum() {
        sumField.setText(String.valueOf(series.sum()));
    }

    private void UpdateSeries() {
        seriesField.setText(series.toString());
    }

    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // place custom component creation code here
    }
}
