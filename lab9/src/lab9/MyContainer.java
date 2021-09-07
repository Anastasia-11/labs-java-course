package lab9;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MyContainer<T extends Student> extends ArrayList<Student> {
    public void readStudentsFromTxtFile(File file) throws FileNotFoundException {
        int gradebookID, course, group;
        String surname;
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            gradebookID = scanner.nextInt();
            surname = scanner.next();
            course = scanner.nextInt();
            group = scanner.nextInt();
            this.add(new Student(gradebookID, surname, course, group));
        }
        scanner.close();
    }

    public void readStudentsFromXmlFile(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        document.getDocumentElement().getNodeName();
        NodeList nodeList = document.getElementsByTagName("Student");

        for (int i = 0; i < nodeList.getLength(); i++) {
            this.add(getStudent(nodeList.item(i)));
        }
    }

    private Student getStudent(Node node) {
        int gradebookID = 0, course = 0, group = 0;
        String surname = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            gradebookID = Integer.parseInt(getTagValue("GradebookID", element));
            surname = getTagValue("Surname", element);
            course = Integer.parseInt(getTagValue("Course", element));
            group = Integer.parseInt(getTagValue("Group", element));
        }
        return new Student(gradebookID, surname, course, group);
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public ArrayList<Student> getStudentsByRepeatedSurnames() {
        ArrayList<String> surnames = new ArrayList<>();
        ListIterator<Student> it = this.listIterator();
        while (it.hasNext()) {
            surnames.add(it.next().getSurname());
        }
        /*for(var item : this) {
            surnames.add(item.getSurname());
        }*/
        return this.stream().filter(e -> Collections.frequency(surnames, e.getSurname()) > 1).sorted(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int result = Integer.compare(s1.getCourse(), s2.getCourse());
                if(result == 0) {
                    result = Integer.compare(s1.getGroup(), (s2.getGroup()));
                }
                if(result == 0) {
                    return s1.getSurname().compareTo(s2.getSurname());
                }
                return result;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> getStudentsByGroup(int group) {
        return this.stream().filter(e -> e.getGroup() == group).sorted(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getGradebookID(), s2.getGradebookID());
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> getStudentsByCourse(int course) {
        return this.stream().filter(e -> e.getCourse() == course).sorted(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int result = Integer.compare(s1.getGroup(), s2.getGroup());
                if(result == 0) {
                    return s1.getSurname().compareTo(s2.getSurname());
                }
                return result;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public void saveAsXML() throws ParserConfigurationException, TransformerException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Output.xml"));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElementNS(null, "Students");
            document.appendChild(root);

            ListIterator<Student> it = this.listIterator();
            while (it.hasNext()) {
                root.appendChild(createStudent(document, it.next()));
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);
        }
    }

    private Node createStudent(Document document, Student student) {
        Element resultStudent = document.createElement("Student");

        resultStudent.appendChild(createStudentElement(document, "GradebookID", String.valueOf(student.getGradebookID())));
        resultStudent.appendChild(createStudentElement(document, "Surname", String.valueOf(student.getSurname())));
        resultStudent.appendChild(createStudentElement(document, "Course", String.valueOf(student.getCourse())));
        resultStudent.appendChild(createStudentElement(document, "Group", String.valueOf(student.getGroup())));

        return resultStudent;
    }

    private Node createStudentElement(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }

    // насколько я поняла, ручная генерация в условии подразумевает это
    public void saveAsXMLManual() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Output.xml"));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            StringBuilder xmlString = new StringBuilder();
            xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xmlString.append("<Students>\n");

            for (Student student : this) {
                xmlString.append("\t<Student>\n");
                xmlString.append("\t\t<GradebookID>").append(student.getGradebookID()).append("</GradebookID>").append('\n');
                xmlString.append("\t\t<Surname>").append(student.getSurname()).append("</Surname>").append('\n');
                xmlString.append("\t\t<Course>").append(student.getCourse()).append("</Course>").append('\n');
                xmlString.append("\t\t<Group>").append(student.getGroup()).append("</Group>").append('\n');
                xmlString.append("\t</Student>\n");
            }

            xmlString.append("</Students>\n");
            FileWriter writer = new FileWriter(file);
            writer.write(xmlString.toString());
            writer.close();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ListIterator<Student> it = this.listIterator();
        while (it.hasNext()) {
            builder.append(it.next().toString()).append('\n');
        }
        return builder.toString();
    }
}