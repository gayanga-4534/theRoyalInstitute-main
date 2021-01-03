package theRoyalInstitute.dao;

import theRoyalInstitute.dao.custom.StudentDAO;
import theRoyalInstitute.dao.custom.impl.CourseDAOimpl;
import theRoyalInstitute.dao.custom.impl.RegistrationDAOimpl;
import theRoyalInstitute.dao.custom.impl.StudentDAOimpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOtypes{
        Student,Course,Registration
    }

    public SuperDAO getDAO(DAOtypes type){
        switch (type){
            case Student:return new StudentDAOimpl();
            case Course:return new CourseDAOimpl();
            case Registration:return new RegistrationDAOimpl();
            default:return null;
        }
    }

    public DAOFactory getInstance(){
        if(daoFactory==null) {daoFactory=new DAOFactory();}
        return daoFactory;
    }
}
