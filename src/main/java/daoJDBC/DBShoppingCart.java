package daoJDBC;

import clases.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBShoppingCart extends DBConnection{

    public ArrayList<Product> showProductsFromShoppingCart(int idUser){
        showList(importDBtoListForShoppingCart(idUser));
        return importDBtoListForShoppingCart(idUser);
    }

    public ArrayList<Product> addShoppingCart(int id_user, int id_product) {
        String query1 = "INSERT INTO shopping_cart (id_user, id_product) \n" +
                " VALUES (" + id_user + ", " + id_product + ");";
        try {
            stmt.executeUpdate(query1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForShoppingCart(id_user);
    }

    public ArrayList<Product> removeOneProduct(int idUser, int idProduct) {
        try {
            String query = "DELETE FROM shopping_cart WHERE id_user = " + idUser + " and id_product = " + idProduct;
            int rows = stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForShoppingCart(idUser);
    }
    public ArrayList<Product> removeAllProduct(int idUser) {
        try {
            String query = "DELETE FROM shopping_cart WHERE id_user = " + idUser;
            int rows = stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForShoppingCart(idUser);
    }
}
