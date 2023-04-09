package daoJDBC;

import clases.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBProducts extends DBConnection{

    public static ArrayList<Product> showProducts(){
        showList(importDBtoListForProduct());
        return importDBtoListForProduct();
    }

    public Product getProduct(int idProduct){
        return importDBtoListForProduct().get(idProduct - 1);
    }

    public ArrayList<Product> addProduct(String name, int number, double price){
        String query = "INSERT INTO products (product_name, number, price) \n" +
                " VALUES ('"+name+"', "+number+", "+price+");";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForProduct();
    }

    public ArrayList<Product> removeProduct(int idProduct){
        try {
            String query = "DELETE FROM Products WHERE id_product = "+idProduct;
            int rows = stmt.executeUpdate(query);
            DBConnection.updateAI(TableName.products);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return importDBtoListForProduct();
    }

    public ArrayList<Product> updateProduct(int id, String name, int number, double price){
        try{
            String query = "Update products set product_name = '"+name+"', number = "+number+", price = "+price+" WHERE id_product = "+id;
            int rows = stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForProduct();
    }
}
