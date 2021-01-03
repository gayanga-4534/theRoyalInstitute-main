package theRoyalInstitute.dto;

public class CourseTable {
    private String Stu_id;
    private String Stu_name;
    private String Stu_reg_date;
    private String course_name;
    private int Contact;

    public CourseTable() {
    }

    public CourseTable(String stu_id, String stu_name, String stu_reg_date, String course_name, int contact) {
        Stu_id = stu_id;
        Stu_name = stu_name;
        Stu_reg_date = stu_reg_date;
        this.course_name = course_name;
        Contact = contact;
    }

    public String getStu_id() {
        return Stu_id;
    }

    public void setStu_id(String stu_id) {
        Stu_id = stu_id;
    }

    public String getStu_name() {
        return Stu_name;
    }

    public void setStu_name(String stu_name) {
        Stu_name = stu_name;
    }

    public String getStu_reg_date() {
        return Stu_reg_date;
    }

    public void setStu_reg_date(String stu_reg_date) {
        Stu_reg_date = stu_reg_date;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getContact() {
        return Contact;
    }

    public void setContact(int contact) {
        Contact = contact;
    }
}
