package jUnitTests;

import clases.Order;
import daoHibernate.DAOOrders;
import daoHibernate.HibernateUtil;
import daoJDBC.DBConnection;
import daoJDBC.DBOrders;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DAOOrdersTest {

    @Test
    void saveOrders() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Order order1 = new Order("cap", 2, 4, 1);
        DAOOrders.saveOrders("cap", 2, 4, 1);
        ArrayList<Order> orders = DBConnection.importDBtoListForOrders(2);
        boolean corect = false;
        for (Order order : orders) {
            if(order.getUserId() == order1.getUserId() && order.getTotalPrice() == order1.getTotalPrice() && order.getNumberProduct() == order1.getNumberProduct())
                corect = true;
        }
        if(!corect)
            throw new RuntimeException();

        DAOOrders.removeOrderByID(DAOOrders.getOrderById(orders.get(orders.size()-1).getId()).getId());

        DBConnection.updateAIAll();
    }

    @Test
    void removeOrderById() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        Order order1 = new Order("cap", 2, 4, 1);
        DAOOrders.saveOrders("cap", 2, 4, 1);
        boolean corect = false;
        ArrayList<Order> orders = DBConnection.importDBtoListForOrders(2);
        DAOOrders.removeOrderByID(DAOOrders.getOrderById(orders.get(orders.size()-1).getId()).getId());
        orders = DBConnection.importDBtoListForOrders(2);
        for (Order order : orders) {
            if(order.getUserId() == order1.getUserId() && order.getTotalPrice() == order1.getTotalPrice() && order.getNumberProduct() == order1.getNumberProduct())
                corect = true;
        }
        if(corect)
            throw new RuntimeException();
    }


    @Test
    void getOrderById() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Order order1 = DAOOrders.getOrderById(1);
        Order order2 = session.get(Order.class, 1);

        if(order1.getId() != order2.getId())
            throw new RuntimeException();
    }

    @Test
    public void tearDown() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        DBConnection.updateAIAll();
    }
}