package daoHibernate;

import clases.Product;
import clases.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import static daoHibernate.DAOShoppingCart.removeAllProductByUserID;

public class OrderService {
    public static void buyProductByUserID(int id){
        HibernateUtil.writeMessagesToLogsFile("Куплено продукти з корзини користувача під ід: "+id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> products = session.get(User.class, id).getProducts();
        String arrProducts = "";
        double totalPrice = 0;
        int number = 0;
        for (Product product : products) {
            arrProducts+=product.getName()+";";
            totalPrice += product.getPrice();
            number++;
        }
        DAOOrders.saveOrders(arrProducts, id, totalPrice, number);
        removeAllProductByUserID(id);
        transaction.commit();
        session.close();
    }
}
