package assignment1;

import java.util.Objects;

public class Course implements Cloneable{
    private String number;
    private String coursename;
    public void CourseInfo(String number, String coursename) {
        this.number = number;
        this.coursename = coursename;
    }
    public String getNumber() {
        return number;
    }
    public String getName() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Override
    public String toString() {
        return number + coursename;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getNumber(), course.getNumber()) && Objects.equals(coursename, course.coursename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), coursename);
    }
    // shallow clone
    // Default version of clone() method. It creates shallow copy of an object.

    protected Object clone() throws CloneNotSupportedException {
        Course clone = new Course();
        this.number = clone.number;
        this.coursename = clone.coursename;
        return clone;
    }
}
