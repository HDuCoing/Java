package assignment1;

import java.util.List;
import java.util.Objects;

public class Student implements Cloneable{
    private String surname;
    private String firstname;

    private String dob;

    public void student(String surname, String firstname, String id, String dob){
        this.surname = surname;
        this.firstname = firstname;

        this.dob = dob;
    }
    public String getSurname(){
        return surname;
    }
    public String getFirstname(){
        return firstname;
    }

    public String getDob(){
        return dob;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @Override
    public String toString() {
        Course courseObjects = new Course();
        return surname + ", " + firstname + "; " + dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getSurname(), student.getSurname()) && Objects.equals(getFirstname(), student.getFirstname()) && Objects.equals(getDob(), student.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurname(), getFirstname(), getDob());
    }

    // mix of deep and shallow clone
    public Student clone() {
        Student clone = new Student();
        this.surname = clone.surname;
        this.firstname = clone.firstname;
        this.dob = clone.dob;
        return clone();
    }

}
