package model;

public class Pupil {
    private String surname;
    private String name;
    private int group;
    private int point;
    private int school;


    public Pupil(String surname, String name, int group, int point) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.point = point;
    }

    public Pupil(String surname, String name, int group, int point, int school) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.point = point;
        this.school = school;
    }

    public Pupil() {
        this.surname = "";
        this.name = "";
        this.group = 0;
        this.point = 0;
        this.school = 0;
    }

    //alt+insert - create new constructor
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", group=" + group +
                ", point=" + point +
                ", school=" + school +
                '}';
    }
}

