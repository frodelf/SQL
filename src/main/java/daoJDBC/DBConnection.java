package daoJDBC;

import clases.Order;
import clases.Product;
import clases.User;
import clases.UserDetails;
import daoHibernate.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/shop";
    private static final String user = "root";
    private static final String password = "1234";

    protected static Connection con;
    protected static Statement stmt;
    protected static ResultSet rs;

    public void dataBaseConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

        } catch (SQLException e) {
            System.out.println("We aren`t connected");
        }
    }
    public static void showList(ArrayList list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public static void showList(List list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public static ArrayList<Product> importDBtoListForProduct() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String query = "select * from products";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int number = rs.getInt(3);
                double price = rs.getDouble(4);
                list.add(new Product(id, name, number, price));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static ArrayList<User> importDBtoListForUser() {
        ArrayList<User> list = new ArrayList<>();
        try {
            String query = "select * from users inner join userdetails on id_user = user_id";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                double money = rs.getDouble(4);
                list.add(new User(id, name, password, money));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static ArrayList<Product> importDBtoListForShoppingCart(int idUser) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String query = "select * from shopping_cart inner join products on shopping_cart.id_product = products.id_product\n" +
                    "where id_user = " + idUser;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(2);
                String name = rs.getString(4);
                double price = rs.getDouble(6);
                list.add(new Product(id, name, 1, price));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ArrayList<UserDetails> importDBtoListForDetails() {
        ArrayList<UserDetails> list = new ArrayList<>();
        try {
            String query = "select * from userdetails";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String surname = rs.getString(2);
                list.add(new UserDetails(id,surname));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ArrayList<Order> importDBtoListForOrders(int idUser) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String query = "select * from orders inner join users on user_id = id_user " +
                    "where id_user = " + idUser;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                int userId = rs.getInt(3);
                int number = rs.getInt(5);
                String products = rs.getString(2);
                double price = rs.getDouble(4);
                list.add(new Order(id, products, userId, price, number));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static int valueDB(TableName name){
        try {
            String query = "select count(1) from " + name.toString();
            rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void updateAI(TableName name){
        try{
            String query = "alter table " + name.toString() + " auto_increment = " + valueDB(name);
            System.out.println(query);
            boolean AI = stmt.execute(query);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void updateAIAll(){
        updateAI(TableName.products);
        updateAI(TableName.userdetails);
        updateAI(TableName.users);
        updateAI(TableName.orders);
    }
}