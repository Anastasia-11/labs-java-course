package lab8;

import javax.swing.*;
import java.awt.*;

public class Panel3 extends JPanel {
    private final int cols, rows;
    private Icon[] icons;

    /*MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            selectedButton.setIcon(icons[0]);
            selectedButton = (JRadioButton)e.getComponent();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JRadioButton radioButton = (JRadioButton)e.getComponent();
            radioButton.setIcon(icons[2]);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JRadioButton radioButton = (JRadioButton)e.getComponent();
            if(radioButton.isSelected()) {
                radioButton.setIcon(icons[1]);
            }
            else {
                radioButton.setIcon(icons[0]);
            }
        }
    };*/

    public Panel3() {
        cols = 3;
        rows = 3;

        initializeIcons();
        setLayout(new GridLayout(rows, cols, 5, 5));
        addRadioButtons();
    }

    private void initializeIcons() {
        icons = new Icon[] {
                new ImageIcon("icon1.png"), // not selected
                new ImageIcon("icon2.png"), // selected
                new ImageIcon("icon3.png"), // mouse is over
                new ImageIcon("icon4.png")  // selected && over
        };
    }

    private void addRadioButtons() {
        int count = cols * rows;
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[count];
        for (int i = 0; i < count; i++) {
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setIcon(icons[0]);
            buttonGroup.add(radioButtons[i]);
            radioButtons[i].setSelectedIcon(icons[1]);
            radioButtons[i].setRolloverIcon(icons[2]);
            radioButtons[i].setRolloverSelectedIcon(icons[3]);
            add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);
    }
}
