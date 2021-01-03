package theRoyalInstitute.util;

import theRoyalInstitute.entity.Course;
import theRoyalInstitute.entity.Registration;
import theRoyalInstitute.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class hibernateUtil {
    private static SessionFactory sessionFactory= createSesstion();

    private static SessionFactory createSesstion() {
        StandardServiceRegistry stg=new StandardServiceRegistryBuilder().loadProperties("theRoyalInstitute/application.properties").build();
        Metadata meta = new MetadataSources(stg).addAnnotatedClass(Student.class).addAnnotatedClass(Registration.class).addAnnotatedClass(Course.class).getMetadataBuilder().build();
        return meta.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
