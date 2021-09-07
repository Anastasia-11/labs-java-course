package lab9;

public class Main {
    public static void main(String[] args) {
        Dialog1 dialog = new Dialog1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);

        /*try {
            MyContainer<Student> students = new MyContainer<>();
            students.readStudentsFromTxtFile(new File("students.txt"));

            System.out.println("getStudentsByRepeatedSurnames");
            ArrayList<Student> l1 = students.getStudentsByRepeatedSurnames();
            l1.forEach(e -> System.out.println(e.toString() + '\n'));

            System.out.println("getStudentsByCourse");
            ArrayList<Student> l2 = students.getStudentsByCourse(1);
            l2.forEach(e -> System.out.println(e.toString() + '\n'));

            System.out.println("getStudentsByGroup");
            ArrayList<Student> l3 = students.getStudentsByGroup(4);
            l3.forEach(e -> System.out.println(e.toString() + '\n'));
        }
        catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!");
        }*/
    }
}
