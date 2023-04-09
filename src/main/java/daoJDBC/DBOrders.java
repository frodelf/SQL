package daoJDBC;

import clases.Order;

import java.util.ArrayList;

public class DBOrders extends DBConnection{
    public ArrayList<Order> showOrders() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String query = "select * from orders inner join users on user_id = id_user";
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



    public ArrayList<Order> buyProduct(int idUser) {
        try {
            String allProducts = "select * from shopping_cart inner join products on shopping_cart.id_product = products.id_product where id_user = " + idUser;
            rs = stmt.executeQuery(allProducts);
            double totalPrice = 0;
            String arrayProducts = "";
            int number = 0;
            while (rs.next()) {
                arrayProducts += rs.getString(4) + ";";
                totalPrice += rs.getInt(6);
                number++;
            }
            new DBUsers().updateUser(idUser, new DBUsers().getUser(idUser).getName(), new DBUsers().getUser(idUser).getPassword(), new DBUsers().getUser(idUser).getMoney() - totalPrice, new DBUsers().getUser(idUser).getSurname());
            String query = "INSERT INTO orders (array_products, user_id, total_price, number_product) \n" +
                    " VALUES ('" + arrayProducts + "', " + idUser + ", " + totalPrice + ", " + number + ");";
            stmt.executeUpdate(query);

            new DBShoppingCart().removeAllProduct(idUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForOrders(idUser);
    }

    public ArrayList<Order> showOneOrders(int idUser) {
        showList(importDBtoListForOrders(idUser));
        return importDBtoListForOrders(idUser);
    }
}
