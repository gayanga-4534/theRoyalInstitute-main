package theRoyalInstitute.controller;

import theRoyalInstitute.alertBox.AlberBox;
import theRoyalInstitute.entity.Course;
import theRoyalInstitute.entity.CustomEntity;
import theRoyalInstitute.entity.Registration;
import theRoyalInstitute.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import theRoyalInstitute.util.hibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    //Course details
    public TextField p_id;
    public TextField p_name;
    public TextField duration;
    public TextField fee;
    public ComboBox pro_type;

    //StudentDetails
    public TextField s_id;
    public TextField s_name;
    public TextField s_address;
    public TextField s_contact;
    public DatePicker bod;
    public RadioButton male;
    public RadioButton female;

    //StudentRegistrationforCourse
    public ComboBox studentIDs;
    public TextField reg_num;
    public TextField reg_fee;
    public DatePicker reg_date;
    public ComboBox course;

    //coursedetails
    public ComboBox courseBox;
    public TableColumn<CustomEntity, String> StudentID;
    public TableColumn<CustomEntity, String> S_name;
    public TableColumn<CustomEntity, String> Reg_date;
    public TableColumn<CustomEntity, String> course_name;
    public TableColumn<CustomEntity, String> contact_num;
    public TableView table;

    List<Course> C_list = null;
    List<Student> list = null;
    List<Registration> allreglist = null;
    List allreg = new ArrayList();

    Transaction transaction = null;

    //tablelist
    ObservableList<CustomEntity> listfortbl= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        S_name.setCellValueFactory(new PropertyValueFactory<>("s_name"));
        Reg_date.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        course_name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        contact_num.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadAllStudents();
        loadAllCourses();
        loadAllCouseDetails();
        pro_type.getItems().addAll("Part time", "Full time");

    }

    private void loadAllCouseDetails() {
        for (int i = 0; i < C_list.size(); i++) {
            courseBox.getItems().add(C_list.get(i).getCourseName());
        }
    }

    private void loadAllCourses() {
        try (Session session = hibernateUtil.getInstance().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Course> c_query = criteriaBuilder.createQuery(Course.class);
            Root<Course> root = c_query.from(Course.class);
            Query<Course> query = session.createQuery(c_query);
            C_list = query.getResultList();
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }

        for (int i = 0; i < C_list.size(); i++) {
            course.getItems().add(C_list.get(i).getCourseName());
        }
    }

    private void loadAllStudents() {
        try (Session session = hibernateUtil.getInstance().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
            Root<Student> root = criteriaQuery.from(Student.class);
            criteriaQuery.select(root);
            Query<Student> query = session.createQuery(criteriaQuery);
            list = query.getResultList();
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }

        for (int i = 0; i < list.size(); i++) {
            studentIDs.getItems().add(list.get(i).getId());
        }
    }

    public void finishAction(ActionEvent actionEvent) {
        try (Session session = hibernateUtil.getInstance().openSession()) {
            Course course = new Course(p_id.getText(), p_name.getText(), pro_type.getSelectionModel().getSelectedItem().toString(), duration.getText(), Double.parseDouble(fee.getText()));
            session.save(course);
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }
    }

    public void finishReg(ActionEvent actionEvent) {
        boolean r1=false;
        try (Session session = hibernateUtil.getInstance().openSession()) {
            Student student = new Student(s_id.getText(), s_name.getText(), s_address.getText(), Integer.parseInt(s_contact.getText()),
                    male.getText(), bod.getValue().toString());
            session.save(student);
            transaction = session.beginTransaction();
            transaction.commit();
            r1=true;
            if (r1==true)
            AlberBox.showInformationA("Succesfull registration...");
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }
    }


    public void finishCourseReg(ActionEvent actionEvent) {
        boolean r1=false;
        try (Session session = hibernateUtil.getInstance().openSession()) {
            transaction = session.beginTransaction();
            int selectedIndex = studentIDs.getSelectionModel().getSelectedIndex();
            Student student = list.get(selectedIndex);
            Student loadedStu = session.load(Student.class, student.getId());
            int selectedIndex1 = course.getSelectionModel().getSelectedIndex();
            Course co = C_list.get(selectedIndex1);
            Course loadedCou = session.load(Course.class, co.getCode());
            Registration registration = new Registration(reg_num.getText(), reg_date.getValue().toString(),
                    Double.parseDouble(reg_fee.getText()), loadedStu, loadedCou);
            loadedStu.getRegistration().add(registration);
            loadedCou.getList().add(registration);
            session.save(registration);
            transaction.commit();
            r1=true;
            AlberBox.showInformationA("Succesfull registration...");
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }
    }

    public void refreshbtn(ActionEvent actionEvent) {
        clearAllFields();
    }

    private void clearAllFields() {
        course.getItems().clear();
        studentIDs.getItems().clear();
        loadAllStudents();
        loadAllCourses();
    }

    public void checkStudent(ActionEvent actionEvent) {
        boolean r = false;
        int selectedIndex = course.getSelectionModel().getSelectedIndex();
        Course co = C_list.get(selectedIndex);
        int selectedIndex1 = studentIDs.getSelectionModel().getSelectedIndex();
        Student student2 = list.get(selectedIndex1);
        Registration load = null;
        try (Session session = hibernateUtil.getInstance().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Registration> criteriaQuery = criteriaBuilder.createQuery(Registration.class);
            Root<Registration> root = criteriaQuery.from(Registration.class);
            criteriaQuery.select(root);
            Query<Registration> query = session.createQuery(criteriaQuery);
            allreglist = query.getResultList();
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }
        for (int i = 0; i < allreglist.size(); i++) {
            if (co.getCode().equals(allreglist.get(i).getCourse().getCode()) & student2.getId().equals
                    (allreglist.get(i).getStudent().getId())) {
                r = true;
                break;
            }
        }
        if (r) {
            AlberBox.showInformationA("This student is already registered for This course");
        }
    }

    public void loadAlldetails(ActionEvent actionEvent) {
        table.getItems().clear();
        listfortbl.clear();
        int selectedIndex = courseBox.getSelectionModel().getSelectedIndex();
        Course course = C_list.get(selectedIndex);
        try (Session session = hibernateUtil.getInstance().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query = session.createNativeQuery("SELECT regNo,Student_id,s_name, regDate,Course.courseName,Student.contact FROM Registration INNER JOIN Student ON Registration.Student_id=Student.id INNER JOIN Course ON Registration.CourseCode=Course.code");
            query.setResultTransformer(Transformers.aliasToBean(CustomEntity.class));
            ArrayList<CustomEntity> queries = (ArrayList<CustomEntity>) query.getResultList();
            transaction.commit();
            for (int i=0; i<queries.size(); i++){
                listfortbl.addAll(queries.get(i));
            }
            table.getItems().addAll(listfortbl);
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }
    }

}

//    select s.s_name, r.Student_id, s.contact, r.regDate from Registration r inner join Student s on r.Student_id=s.id where r.CourseCode="C0001";