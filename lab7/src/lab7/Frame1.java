package lab7;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JScrollPane;

public class Frame1 extends JFrame {
    private final DrawPanel panel1;
    private Color color;
    private Graphics graphics;
    private JMenuItem open, save, clear;
    private int mousePosX, mousePosY;

    public Frame1() {
        int width = 800;
        int height = 650;

        panel1 = new DrawPanel();
        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setPreferredSize(new Dimension(750, 700));
        JButton colorButton = new JButton("Choose color");
        color = new Color(24, 68, 199);

        getContentPane().setLayout(new BorderLayout());
        setJMenuBar(createMenuBar());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(colorButton, BorderLayout.BEFORE_FIRST_LINE);

        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        panel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosX = e.getXOnScreen() - panel1.getLocationOnScreen().x;
                mousePosY = e.getYOnScreen() - panel1.getLocationOnScreen().y;
            }
        });

        panel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            int x = e.getXOnScreen() - panel1.getLocationOnScreen().x;
            int y = e.getYOnScreen() - panel1.getLocationOnScreen().y;
                if(coordinatesCorrect(x, y)){
                    graphics = panel1.getGraphics();
                    Graphics graphics1 = panel1.getBufferedImage().getGraphics();
                    graphics1.setColor(color);
                    graphics1.drawLine(x, y, mousePosX, mousePosY);
                    graphics.drawImage(panel1.getBufferedImage(), 0, 0, null);
                    mousePosX = x;
                    mousePosY = y;
                }
            }
        });

        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            int x = e.getXOnScreen() - panel1.getLocationOnScreen().x;
            int y = e.getYOnScreen() - panel1.getLocationOnScreen().y;
                if(coordinatesCorrect(x, y)){
                    graphics = panel1.getGraphics();
                    Graphics graphics1 = panel1.getBufferedImage().getGraphics();
                    graphics1.setColor(color);
                    graphics1.fillOval(x, y, 2, 2);
                    graphics.drawImage(panel1.getBufferedImage(), 0, 0, null);
                }
            }
        });

        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(getContentPane(), "Choosing color...", color);
            if(newColor != null) {
                color = newColor;
            }
            repaint();
        });

        open.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    BufferedImage img = ImageIO.read(file);
                    if(img != null) {
                        panel1.setBufferedImage(img);
                        panel1.setPreferredSize(new Dimension(panel1.getBufferedImage().getWidth(), panel1.getBufferedImage().getHeight()));
                        graphics = panel1.getGraphics();
                        graphics.drawImage(panel1.getBufferedImage(), 0, 0, null);
                        panel1.revalidate();
                        panel1.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Wrong extension of the file!");
                    }
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Error! Can't open the file!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Exception!");
                }
            }
        });

        save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("file.png"));
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    ImageIO.write(panel1.getBufferedImage(), "png", file);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Error! Can't save the picture!");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Exception");
                }
            }
        });

        clear.addActionListener(e -> {
            graphics = panel1.getGraphics();
            Graphics graphics1 = panel1.getBufferedImage().getGraphics();
            graphics1.setColor(Color.WHITE);
            graphics1.fillRect(0, 0, panel1.getWidth(), panel1.getHeight());
            graphics.drawImage(panel1.getBufferedImage(), 0, 0, null);
        });
    }

    private boolean coordinatesCorrect(int x, int y) {
        int maxX = panel1.getWidth();
        int maxY = panel1.getHeight();
        return x > 0 && x < maxX && y > 0 && y < maxY;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        clear = new JMenuItem("Clear");
        menu.add(open);
        menu.add(save);
        menu.addSeparator();
        menu.add(clear);
        menuBar.add(menu);
        return menuBar;
    }
}
