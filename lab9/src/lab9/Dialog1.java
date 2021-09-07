package lab9;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Dialog1 extends JDialog {
    private JPanel contentPane;
    private JTextField gradebookIDTextField, groupTextField, surnameTextField, courseTextField;
    private JButton addNewStudentButton, getBySurnameButton, getByGroupButton, getByCourseButton;
    private JTextArea listView, listFunctionView;
    private JTextField courseToFindTextField, groupToFindTextField;
    private JButton loadDataFromTxtButton, loadDataFromXmlButton;
    private JButton saveAsXmlButton, saveAsXMLManualButton;
    private MyContainer<Student> container;

    public Dialog1() {
        setContentPane(contentPane);
        setModal(true);

        container = new MyContainer<>();

        addNewStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(gradebookIDTextField.getText());
                    String surname = surnameTextField.getText();
                    int group = Integer.parseInt(groupTextField.getText());
                    int course = Integer.parseInt(courseTextField.getText());
                    container.add(new Student(id, surname, course, group));
                    listView.setText(container.toString());
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Incorrect input data!");
                }
            }
        });

        getBySurnameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> students = container.getStudentsByRepeatedSurnames();
                update(students, listFunctionView);
            }
        });

        getByCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int course = Integer.parseInt(courseToFindTextField.getText());
                    ArrayList<Student> students = container.getStudentsByCourse(course);
                    update(students, listFunctionView);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Incorrect input data!");
                }
            }
        });

        getByGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int group = Integer.parseInt(groupToFindTextField.getText());
                    ArrayList<Student> students = container.getStudentsByGroup(group);
                    update(students, listFunctionView);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Incorrect input data!");
                }
            }
        });

        loadDataFromTxtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        if(extensionIsCorrect(file, "txt")) {
                            container.clear();
                            container.readStudentsFromTxtFile(file);
                            listView.setText(container.toString());
                            listFunctionView.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong extension of the file!");
                        }
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null, "Error! Can't open the file!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Exception!");
                    }
                }
            }
        });

        loadDataFromXmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        if(extensionIsCorrect(file, "xml")) {
                            container.clear();
                            container.readStudentsFromXmlFile(file);
                            listView.setText(container.toString());
                            listFunctionView.setText("");
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
            }
        });

        saveAsXmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(container.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Container is empty!");
                        return;
                    }
                    container.saveAsXML();
                } catch (ParserConfigurationException parserConfigurationException) {
                    JOptionPane.showMessageDialog(null, "ParserConfigurationException!");
                } catch (TransformerException transformerException) {
                    JOptionPane.showMessageDialog(null, "TransformerException!");
                }
            }
        });

        saveAsXMLManualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(container.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Container is empty!");
                        return;
                    }
                    container.saveAsXMLManual();
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Error while writing data to the file!");
                }
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void update(ArrayList<Student> list, JTextArea textArea) {
        StringBuilder builder = new StringBuilder();
        ListIterator<Student> it = list.listIterator();
        while(it.hasNext()) {
            builder.append(it.next().toString()).append('\n');
        }
        /*for(Student student : list) {
            builder.append(student.toString()).append('\n');
        }*/
        textArea.setText(builder.toString());
    }

    private boolean extensionIsCorrect(File file, String extension) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index == -1 || index == fileName.length() - 1) return false;
        return fileName.substring(index + 1).equals(extension);
    }
}