package lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel1 extends JPanel {
    private JList<String> leftList, rightList;
    private DefaultListModel<String> leftListModel, rightListModel;
    private JButton buttonToLeft, buttonToRight;

    Panel1() {
        int width = 200, height = 200;
        setLayout(new BorderLayout());

        leftListModel = new DefaultListModel<>();
        rightListModel = new DefaultListModel<>();

        leftList = new JList<>(leftListModel);
        rightList = new JList<>(rightListModel);

        leftList.setPreferredSize(new Dimension(width, height));
        rightList.setPreferredSize(new Dimension(width, height));

        buttonToLeft = new JButton("<-");
        buttonToRight = new JButton("->");

        addDataToList();
        addComponents();

        buttonToRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchSelectedElements(leftList, rightList);
            }
        });

        buttonToLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchSelectedElements(rightList, leftList);
            }
        });
    }

    private void switchSelectedElements(JList<String> list1, JList<String> list2) {
        int[] indexes = list1.getSelectedIndices();
        DefaultListModel<String> model1 = (DefaultListModel<String>) list1.getModel();
        DefaultListModel<String> model2 = (DefaultListModel<String>) list2.getModel();
        for (int index : indexes) {
            model2.addElement(model1.getElementAt(index));
        }
        for(int i = 0; i < indexes.length; i++) {
            model1.remove(indexes[indexes.length - i - 1]);
        }
    }

    private void addDataToList() {
        String[] data = new String[]{
                "Реализовать приложение",
                "со следующим", "интерфейсом",
                "(только компоновка",
                "BorderLayout).",
                "Кнопки перемещают",
                "выделенные элементы",
                "(все сразу) из",
                "одного списка",
                "в другой.",
                "Элементы списка",
                "произвольны",
                "Реализовать приложение со",
                "следующим интерфейсом",
                "(компоновка ",
                "GridLayout)."};
        for (String leftListDatum : data) {
            leftListModel.addElement(leftListDatum);
        }
    }

    private void addComponents() {
        add(leftList, BorderLayout.WEST);
        add(rightList, BorderLayout.EAST);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(buttonToLeft, BorderLayout.NORTH);
        centerPanel.add(buttonToRight, BorderLayout.SOUTH);
        add(centerPanel);
    }
}
