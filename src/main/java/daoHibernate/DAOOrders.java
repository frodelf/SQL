package daoHibernate;

import clases.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOOrders {
    public static void saveOrders(String arrProduct, int userID, double totalPrice, int numberProduct) {
        HibernateUtil.writeMessagesToLogsFile("Збережено замовлення користувача: "+userID);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order order = new Order();
            order.setArrProducts(arrProduct);
            order.setUserId(userID);
            order.setTotalPrice(totalPrice);
            order.setNumberProduct(numberProduct);
            session.save(order);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static Order getOrderById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Order.class, id);
    }


    public static void removeOrderByID(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(DAOOrders.getOrderById(id));
        transaction.commit();
        session.close();
    }
}
