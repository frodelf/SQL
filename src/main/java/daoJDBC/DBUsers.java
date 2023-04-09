package daoJDBC;

import clases.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBUsers extends DBConnection{
    public ArrayList<User> showUsers() {
        showList(importDBtoListForUser());
        return importDBtoListForUser();
    }
    public User getUser(int idUser) {
        return importDBtoListForUser().get(idUser -1);
    }
    public ArrayList<User> addUser(String name, String password, double money, String surname) {
        String query1 = "INSERT INTO userdetails (surname) \n" +
                " VALUES ('" + surname + "');";

        String query2 = "INSERT INTO users (name, password, money) \n" +
                " VALUES ('" + name + "', '" + password + "', " + money + ");";

        try {
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DBProducts.importDBtoListForUser();
    }
    public ArrayList<User> removeUser(int id) {
        try {
            String query = "DELETE FROM userdetails WHERE user_id = " + id;
            int rows = stmt.executeUpdate(query);
            query = "DELETE FROM users WHERE id_user = " + id;
            rows = stmt.executeUpdate(query);
            DBConnection.updateAI(TableName.users);
            DBConnection.updateAI(TableName.userdetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForUser();
    }
    public ArrayList<User> updateUser(int id, String name, String password, double money, String surname) {
        try {
            String query = "Update users set name = '" + name + "', password = '" + password + "', money = " + money + " WHERE id_user = " + id;
            int rows = stmt.executeUpdate(query);
            query = "Update userdetails set surname = '" + surname + "' WHERE user_id = " + id;
            rows = stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return importDBtoListForUser();
    }
}
