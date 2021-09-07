package lab6;

import javax.swing.*;
import java.awt.event.*;

public class Dialog2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonYes;
    private JButton buttonNo;
    private final Icon messageIcon;

    public Dialog2() {
        setContentPane(contentPane);
        setModal(true);
        setResizable(false);
        messageIcon = new ImageIcon("smile2.png");

        buttonNo.addActionListener(e -> onButtonNo());

        buttonYes.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                generateNewButtonPos();
            }
    });

        // call dispose() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call dispose() on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void generateNewButtonPos() {
        int minX = buttonYes.getWidth();
        int maxX = contentPane.getWidth() - minX;
        int minY = buttonYes.getHeight();
        int maxY = contentPane.getHeight() - minY;
        int newX = (int) (Math.random() * ((maxX - minX) + 1) + minX);
        int newY = (int) (Math.random() * ((maxY - minY) + 1) + minY);
        setButtonPosition(buttonYes, newX, newY);
    }

    private void setButtonPosition(JButton buttonYes, int newX, int newY) {
        buttonYes.setLocation(newX, newY);
    }

    private void onButtonNo() {
        JOptionPane.showMessageDialog(
                null,
                "Что-то утешающее",
                "Утешающее окно",
                JOptionPane.INFORMATION_MESSAGE, messageIcon);
    }

    public static void main(String[] args) {
        Dialog2 dialog = new Dialog2();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
