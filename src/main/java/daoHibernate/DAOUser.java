package daoHibernate;

import clases.Product;
import clases.User;
import clases.UserDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DAOUser {
    public static List<User> getUsers(){
        HibernateUtil.writeMessagesToLogsFile("Отримуємо всіх користувачів з БД");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);
            Query query = session.createQuery(cq);
            List<User> userList = query.getResultList();
            transaction.commit();
            session.close();
            return userList;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
    public static User getUserByID(int id){
        HibernateUtil.writeMessagesToLogsFile("Отримуємо користувача з БД під ід: "+id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        return user;
    }

    public static void saveUser(User user, UserDetails details) {
        HibernateUtil.writeMessagesToLogsFile("Збережено користувача: "+user.getName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(user);
        session.save(details);
        session.close();
    }

    public static void removeUserByID(int id){
        HibernateUtil.writeMessagesToLogsFile("Видалимо користувача з БД з ід: "+id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(DAOUserDetails.getDetailsByID(id));
        session.remove(DAOUser.getUserByID(id));
        transaction.commit();
        session.close();
    }

    public static void updateUserByID(User user) {
        HibernateUtil.writeMessagesToLogsFile("Обновлено дані користувача: "+user.getName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user1 = session.get(User.class, 1);
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setMoney(user.getMoney());
        session.update(user1);
        transaction.commit();
        session.close();
    }
}
