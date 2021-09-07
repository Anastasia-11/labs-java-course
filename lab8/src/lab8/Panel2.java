package lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Panel2 extends JPanel {
    private final int cols, rows;
    private final Color defaultColor, color;
    private String text;

    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            JButton button = (JButton)e.getComponent();
            text = button.getText();
            if(e.getButton() == MouseEvent.BUTTON1) {
                button.setText("Clicked!");
            }
            else {
                button.setText("!!!!!!");
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton button = (JButton)e.getComponent();
            button.setText(text);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            e.getComponent().setBackground(color);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setBackground(defaultColor);
        }
    };

    public Panel2() {
        cols = 6;
        rows = 6;

        color = new Color(59, 113, 247);
        defaultColor = new Color(213, 245, 247);

        setLayout(new GridLayout(rows, cols, 5, 5));
        addButtons();
    }

    private void addButtons() {
        int count = cols * rows;
        for (int i = 0; i < count; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.addMouseListener(mouseListener);
            button.setBackground(defaultColor);
            add(button);
        }
    }
}
