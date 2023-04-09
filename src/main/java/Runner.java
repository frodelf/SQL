import clases.User;
import clases.UserDetails;
import daoHibernate.*;
import daoJDBC.DBConnection;
import daoJDBC.DBOrders;
import daoJDBC.DBUsers;
import daoJDBC.TableName;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.get(User.class, 3).getProducts().add(DAOProduct.getProductByID(2));
        transaction.commit();
        session.close();
    }
}