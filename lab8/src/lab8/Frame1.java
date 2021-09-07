package lab8;

import javax.swing.*;
import java.awt.*;

public class Frame1 extends JFrame {

    Frame1(){
        int width = 650;
        int height = 650;
        add(createTabbedPane());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setResizable(false);
        setVisible(true);
    }

    private JTabbedPane createTabbedPane() {
        JTabbedPane pane = new JTabbedPane();
        Panel1 panel1 = new Panel1();
        Panel2 panel2 = new Panel2();
        Panel3 panel3 = new Panel3();
        pane.add("Task 1", panel1);
        pane.add("Task 2", panel2);
        pane.add("Task 3", panel3);
        return pane;
    }
}
