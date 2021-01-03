package theRoyalInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomEntity {
    @Id
    private String regNo;
    private String Student_id;
    private String s_name;
    private String regDate;
    private String courseName;
    private int contact;

    public CustomEntity() {
    }

    public CustomEntity(String regNo, String id, String s_name, String regDate, String courseName, int contact) {
        this.regNo = regNo;
        this.Student_id = id;
        this.s_name = s_name;
        this.regDate = regDate;
        this.courseName = courseName;
        this.contact = contact;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getId() {
        return Student_id;
    }

    public void setId(String id) {
        this.Student_id = id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
