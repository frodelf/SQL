package jUnitTests;

import clases.Product;
import clases.User;
import clases.UserDetails;
import daoHibernate.DAOProduct;
import daoHibernate.DAOUser;
import daoHibernate.HibernateUtil;
import daoJDBC.DBConnection;
import daoJDBC.DBProducts;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOProductTest {
    @Test
    void getProductByID() throws ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product1 = session.get(Product.class, 1);
        Product product2 = DAOProduct.getProductByID(1);
        if(!product1.equals(product2)) throw new RuntimeException();
        session.close();
    }

    @Test
    void saveProduct() throws ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int size1 = DBConnection.importDBtoListForProduct().size();
        Product product = new Product("qwerty", 10, 10.3);
        DAOProduct.saveProduct(product);
        int size2 = DBConnection.importDBtoListForProduct().size();
        DAOProduct.removeProduct(size2);
        if(size1 != --size2)throw new RuntimeException();
        DBConnection.updateAIAll();
        removeProduct();
        transaction.commit();
        session.close();
    }

    @Test
    void removeProduct() throws ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int size1 = DBConnection.importDBtoListForProduct().size();
        Product product = new Product("qwerty", 10, 10.3);
        DAOProduct.saveProduct(product);
        DAOProduct.removeProduct(size1+1);
        int size2 = DBConnection.importDBtoListForProduct().size();
        if(size1 != size2)throw new RuntimeException();
        DBConnection.updateAIAll();
        transaction.commit();
        session.close();
    }

    @Test
    public void tearDown() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        DBConnection.updateAIAll();
    }
}