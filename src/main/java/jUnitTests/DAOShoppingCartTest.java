package jUnitTests;

import clases.Product;
import daoHibernate.DAOShoppingCart;
import daoHibernate.DAOUser;
import daoJDBC.DBConnection;
import daoJDBC.DBShoppingCart;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.JVM)
class DAOShoppingCartTest {

    @Test
    void addProductToUsersCartByID() {
        int id = 1;
        List<Product> startProducts = DAOShoppingCart.showProductById(id);
        DAOShoppingCart.addProductToUsersCartByID(id,2);
        DAOShoppingCart.addProductToUsersCartByID(id,3);
        List<Product> lastProducts = DAOShoppingCart.showProductById(id);

        if(startProducts.size() != lastProducts.size()) throw new RuntimeException();
        DAOShoppingCart.removeProductByID(id,2);
        DAOShoppingCart.removeProductByID(id,3);
    }

    @Test
    void showProductById() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int id = 2;
        List<Product> list1 = new DBShoppingCart().showProductsFromShoppingCart(id);
        List<Product> list2 = DAOShoppingCart.showProductById(id);

        for (int i = 0; i < list2.size(); i++) {
            if(list1.get(i).equals(list2.get(i))) throw new RuntimeException();
        }
    }

    @Test
    void removeAllProductByUserID() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int id = 2;
        List<Product> products = DAOShoppingCart.showProductById(id);
        DBConnection.showList(products);
        DAOShoppingCart.removeAllProductByUserID(id);
        if(DAOUser.getUserByID(id).getProducts().size() != 0) throw new RuntimeException();
        for (Product product : products) {
            DAOShoppingCart.addProductToUsersCartByID(id, product.getId());
        }
    }
    @Test
    public void tearDown() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        DBConnection.updateAIAll();
    }
}