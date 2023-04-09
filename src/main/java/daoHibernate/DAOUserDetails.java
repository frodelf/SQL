package daoHibernate;

import clases.User;
import clases.UserDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOUserDetails {
    public static void saveUserDetails(UserDetails details) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(details);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static UserDetails getDetailsByID(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserDetails details = session.get(UserDetails.class, id);
        session.close();
        return details;
    }

}
