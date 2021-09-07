package lab4;

public class Student implements Comparable<Student> {
    private int ID;
    private String name;
    private int group;
    private  int course;

    Student(int ID, String name, int group, int course) {
        this.ID = ID;
        this.name = name;
        this.group = group;
        this.course = course;
    }

    public void Info() {
        System.out.println("Name:" + this.name + '\n' + "Group:" + this.group + '\n' + "Course:" + this.course + '\n');
    }

    @Override
    public int compareTo(Student s) {
        return Integer.compare(ID, s.ID);
    }
}
