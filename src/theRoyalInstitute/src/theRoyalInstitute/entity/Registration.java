package theRoyalInstitute.entity;

import javax.persistence.*;

@Entity
public class Registration {
    @Id
    String regNo;
    String regDate;
    double regFee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Student_id" , referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CourseCode", referencedColumnName = "code" , nullable = false)
    private Course course;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Registration() {
    }

    public Registration(String regNo, String regDate, double regFee, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student = student;
        this.course = course;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
