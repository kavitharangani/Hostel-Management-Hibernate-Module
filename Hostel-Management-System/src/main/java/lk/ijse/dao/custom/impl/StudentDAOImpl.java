package lk.ijse.dao.custom.impl;


import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Student;
import lk.ijse.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

    public Student search(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        Student studentSearch=session.get(Student.class,id);
        transaction.commit();
        session.close();
        return studentSearch;
    }

    public boolean save(Student student) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return false;
    }

    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        Student remove=session.get(Student.class,id);
        session.remove(remove);
        transaction.commit();
        session.close();
        return false;
    }

    public boolean Update(Student student) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return false;
    }

}
