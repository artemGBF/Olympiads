package student;

public class Student {
    private String name;
    private String surname;
    private int point;
    private int univer;

    public Student(String name, String surname, int point, int univer) {
        this.name = name;
        this.surname = surname;
        this.point = point;
        this.univer = univer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", point='" + point + '\'' +
                ", univer='" + univer + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getUniver() {
        return univer;
    }

    public void setUniver(int univer) {
        this.univer = univer;
    }
}
