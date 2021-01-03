package theRoyalInstitute.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    private String id;
    private String s_name;
    private String address;
    private int contact;
    private String bod;
    private String gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registration=new ArrayList<>();

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String s_name, String address, int contact, String gender, String bod) {
        this.id = id;
        this.s_name = s_name;
        this.address = address;
        this.contact = contact;
        this.bod = bod;
        this.gender = gender;
    }

    public void setRegistration(List<Registration> registration) {
        this.registration = registration;
    }

    public List<Registration> getRegistration() {
        return registration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getBod() {
        return bod;
    }

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
