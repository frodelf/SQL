package daoHibernate;

import clases.Product;
import clases.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOProduct {
    public static Product getProductByID(int id){
        HibernateUtil.writeMessagesToLogsFile("Отримано користувача з ід: "+id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        transaction.commit();
        return product;
    }

    public static void saveProduct(Product product){
        HibernateUtil.writeMessagesToLogsFile("Збережено продукт: "+product.getName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(product);
        session.close();
    }

    public static void removeProduct(int id){
        HibernateUtil.writeMessagesToLogsFile("Видалено користувача з ід: "+id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getProductByID(id));
        transaction.commit();
        session.close();
    }
}
