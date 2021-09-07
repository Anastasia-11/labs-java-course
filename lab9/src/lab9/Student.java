package lab9;

public class Student {
    private int gradebookID;
    private String surname;
    private int course;
    private int group;

    public Student(int gradebookID, String surname, int course, int group) {
        this.gradebookID = gradebookID;
        this.surname = surname;
        this.course = course;
        this.group = group;
    }

    public String toString() {
        return "Gradebook ID: " + gradebookID +
                "\nSurname: " + surname +
                "\nCourse: " + course +
                "\nGroup: " + group + '\n';
    }

    public int getGradebookID() {
        return gradebookID;
    }

    public void setGradebookID(int gradebookID) {
        this.gradebookID = gradebookID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}