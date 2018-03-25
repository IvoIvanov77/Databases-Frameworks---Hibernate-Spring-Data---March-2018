package p3_university_system;

import javax.persistence.*;
import java.util.Set;

//@Entity(name = "students")
public class Student extends Person{

    private float averageGrade;

    private int attendance;

    private Set<Course> courses;

    @Basic
    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Basic
    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    @ManyToMany(mappedBy = "students")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
