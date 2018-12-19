package dao;

import Ishay.MySqlUsersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tre.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IHiberDao<MySqlUsersEntity> {
    public static UserDAO OUR_INSTANCE;

    private UserDAO() {
    }

    @Override
    public void add(MySqlUsersEntity item) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
//            session.flush();
            session.close();
        }
    }

    @Override
    public void delete(int id) {
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
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void update(MySqlUsersEntity item) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
//            session.flush();
            session.close();
        }
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
    public MySqlUsersEntity getItemByID(int itemID) {
        MySqlUsersEntity user = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from MySqlUsersEntity where id = :itemID";
            Query query = session.createQuery(queryString);
            query.setInteger("itemID", itemID);
            user = (MySqlUsersEntity) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }
}
