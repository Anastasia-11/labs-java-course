package lab6;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.*;

public class Dialog1 extends JDialog {
    private JPanel contentPanel;
    private JTextField yCoordinateField;
    private JTextField xCoordinateField;
    private JButton button1;
    private JLabel xLabel;
    private JLabel yLabel;
    private JPanel panel1;

    public Dialog1() {
        setContentPane(contentPanel);
        setModal(true);
        panel1.setLayout(null);
        button1.setBounds(50, 50, 100, 30);
        xCoordinateField.setText(String.valueOf(MouseInfo.getPointerInfo().getLocation().x));
        yCoordinateField.setText(String.valueOf(MouseInfo.getPointerInfo().getLocation().y));

        // call dispose() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        button1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                onKey(e);
            }
        });

        button1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(e.isControlDown()) {
                    updateButtonPosition(e);
                }
                updateCoordinates(e.getLocationOnScreen());
            }
        });

        // update coordinates when mouse is over the button
        button1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updateCoordinates(e.getLocationOnScreen());
            }
        });

        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateButtonPosition(e);
            }
        });

        panel1.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updateCoordinates(e.getLocationOnScreen());
            }
        });

        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                xCoordinateField.setText("-");
                yCoordinateField.setText("-");
                /*int x, y, x1, y1, x2, y2;
                int k = 0;
                 do {
                    x = MouseInfo.getPointerInfo().getLocation().x;
                    y = MouseInfo.getPointerInfo().getLocation().y;
                    xCoordinateField.setText(String.valueOf(x));
                    yCoordinateField.setText(String.valueOf(y));
                    x1 = contentPanel.getLocation().x;
                    x2 = contentPanel.getSize().width + x1;
                    y1 = contentPanel.getLocation().y;
                    y2 = contentPanel.getSize().height + y1;
                    k++;
                } while (x < x1 || x > x2 || y < y1 || y > y2);
                k = 10;*/
            }
        });

        // call dispose() on ESCAPE
        contentPanel.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void updateButtonPosition(MouseEvent e) {
        int newX = e.getXOnScreen() - panel1.getLocationOnScreen().x - button1.getWidth() / 2;
        int newY = e.getYOnScreen() - panel1.getLocationOnScreen().y - button1.getHeight() / 2;
        int maxX = panel1.getWidth() - button1.getWidth();
        int maxY = panel1.getHeight() - button1.getHeight();
        if (newX < 0) newX = 0;
        if (newY < 0) newY = 0;
        if (newX > maxX) newX = maxX;
        if (newY > maxY) newY = maxY;
        button1.setLocation(newX, newY);
    }

    private void updateCoordinates(Point mousePosition) {
        xCoordinateField.setText(String.valueOf(mousePosition.x));
        yCoordinateField.setText(String.valueOf(mousePosition.y));
    }

    private void onKey(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (keyChar == KeyEvent.VK_BACK_SPACE) {
            onBackSpace();
        }
        else {
            button1.setSize(button1.getWidth() + 10, button1.getHeight());
            button1.setText(button1.getText() + e.getKeyChar());
        }
    }

    private void onBackSpace() {
        StringBuilder builder = new StringBuilder(button1.getText());
        if(builder.length() != 0) {
            builder.deleteCharAt(builder.length() - 1);
            button1.setSize(button1.getWidth() - 10, button1.getHeight());
            button1.setText(builder.toString());
        }
    }

    public static void main(String[] args) {
        Dialog1 dialog = new Dialog1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // place custom component creation code here
    }
}
