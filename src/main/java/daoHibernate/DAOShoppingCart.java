package daoHibernate;

import clases.Product;
import clases.User;
import daoJDBC.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DAOShoppingCart {
    public static List<Product> showProductById(int id){
        HibernateUtil.writeMessagesToLogsFile("Отримано список продуктів з корзини користувача під ід: "+id);
        List<Product> products;
        Session session = HibernateUtil.getSessionFactory().openSession();
        DBConnection.showList(DAOUser.getUserByID(id).getProducts());
        products = DAOUser.getUserByID(id).getProducts();
        session.close();
        return products;
    }

    public static void removeAllProductByUserID(int id){
        HibernateUtil.writeMessagesToLogsFile("Видалено продукти з корзини користувача під ід: "+id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        user.getProducts().clear();
        transaction.commit();
        session.close();
    }

    public static void removeProductByID(int userId, int productId){
        HibernateUtil.writeMessagesToLogsFile("Видалено конкретний продукт з корзини користувача під ід: "+userId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, userId);
        user.getProducts().remove(DAOProduct.getProductByID(productId));

        transaction.commit();
        session.close();
    }

    public static void addProductToUsersCartByID(int userID, int productID){
        HibernateUtil.writeMessagesToLogsFile("Додано до корзини користувача в якого ід: "+userID+" продукт з ід:"+productID);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.get(User.class, userID).getProducts().add(session.get(Product.class, productID));
        transaction.commit();
        session.close();
    }


}
