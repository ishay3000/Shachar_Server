package dao;

import Ishay.MySqlUsersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tre.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IHiberDao<MySqlUsersEntity> {
    public static UserDAO OUR_INSTANCE = new UserDAO();

    private UserDAO() {
    }

    @Override
    public boolean add(MySqlUsersEntity item) {
        boolean isActionOk = true;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(item);
//            session.getTransaction().commit();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                isActionOk = false;
            }
            e.printStackTrace();
        } finally {
//            session.flush();
            session.close();
        }
        return isActionOk;
    }

    @Override
    public boolean delete(int id) {
        boolean isActionOk = true;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            MySqlUsersEntity user = (MySqlUsersEntity) session.load(MySqlUsersEntity.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
                isActionOk = false;
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return isActionOk;
    }

    @Override
    public boolean update(MySqlUsersEntity item) {
        boolean isActionOk = true;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
                isActionOk = false;
            }
            e.printStackTrace();
        } finally {
//            session.flush();
            session.close();
        }
        return isActionOk;
    }

    @Override
    public List<MySqlUsersEntity> getAllItems() {
        List<MySqlUsersEntity> users = new ArrayList<MySqlUsersEntity>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from MySqlUsersEntity ").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    @Override
    public MySqlUsersEntity getItemByID(Object itemID) {
        // type checking
        if (!(itemID instanceof LoginDetails)) {
            return null;
        }
        MySqlUsersEntity user = null;
        LoginDetails loginDetails = (LoginDetails) itemID;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            trns = session.beginTransaction();
            String queryString = "from MySqlUsersEntity where userid = :uID and password = :pass";
            Query query = session.createQuery(queryString);
            query.setInteger("uID", loginDetails.uID);
            query.setString("pass", loginDetails.pass);
            user = (MySqlUsersEntity) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}
