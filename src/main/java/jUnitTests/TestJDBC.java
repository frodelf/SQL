package jUnitTests;

import daoJDBC.*;

class TestJDBC {
    DBUsers users = new DBUsers();
    DBProducts products = new DBProducts();
    DBOrders orders = new DBOrders();
    DBShoppingCart cart = new DBShoppingCart();

    @org.junit.jupiter.api.Test
    void showProducts() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        if(products.showProducts().size() != DBConnection.valueDB(TableName.products))
            throw new RuntimeException();
    }

    @org.junit.jupiter.api.Test
    void getProduct() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        if(products.getProduct(1).getId() != 1)
            throw new RuntimeException();
    }

    @org.junit.jupiter.api.Test
    void addProduct() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int startLength = DBConnection.valueDB(TableName.products);
        if(++startLength != products.addProduct("ddd", 50, 15).size())
            throw new RuntimeException();
        products.removeProduct(DBConnection.importDBtoListForProduct().size());
    }

    @org.junit.jupiter.api.Test
    void updateProduct() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int id = 1;
        String name = DBConnection.importDBtoListForProduct().get(0).getName();
        int number = DBConnection.importDBtoListForProduct().get(0).getNumber();
        double price = DBConnection.importDBtoListForProduct().get(0).getPrice();
        products.updateProduct(id, "BBB", 3,3);
        if(!DBConnection.importDBtoListForProduct().get(0).getName().equals("BBB"))
            throw new RuntimeException();
        products.updateProduct(id, name, number, price);
    }

    @org.junit.jupiter.api.Test
    void removeProduct() throws ClassNotFoundException{
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        products.addProduct("asdf", 2, 3);
        int id = DBConnection.importDBtoListForProduct().size();
        products.removeProduct(id);
        if(--id != DBConnection.importDBtoListForProduct().size())
            throw new RuntimeException();
        DBConnection.updateAIAll();
    }

    @org.junit.jupiter.api.Test
    void showUsers() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int length = users.showUsers().size();
        if(DBConnection.importDBtoListForUser().size() != length)
            throw new RuntimeException();
        DBConnection.updateAIAll();
    }

    @org.junit.jupiter.api.Test
    void getUser() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        if(users.getUser(1).getId() != 1)
            throw new RuntimeException();
    }

    @org.junit.jupiter.api.Test
    void addUser() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int startLength = DBConnection.valueDB(TableName.users);
        if(++startLength != users.addUser("BigBoss", "BOSS", 100000, "7777").size())
            throw new RuntimeException();
        users.removeUser(DBConnection.importDBtoListForUser().size());
    }

    @org.junit.jupiter.api.Test
    void updateUser() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        int id = 1;
        String name = DBConnection.importDBtoListForUser().get(0).getName();
        String password = DBConnection.importDBtoListForUser().get(0).getPassword();
        double money = DBConnection.importDBtoListForUser().get(0).getMoney();
        String surname = DBConnection.importDBtoListForUser().get(0).getSurname();
        users.updateUser(id, "UpName", "UpPassword",30000, "UpSurname");
        if(!DBConnection.importDBtoListForUser().get(0).getName().equals("UpName") && !DBConnection.importDBtoListForDetails().get(0).getSurname() .equals("UpSurname"))
            throw new RuntimeException();
        users.updateUser(id, name, password, money, surname);
    }

    @org.junit.jupiter.api.Test
    void removeUser() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        users.addUser("asdf", "asdfg", 3000, "qwerty");
        int id = DBConnection.importDBtoListForUser().size();
        users.removeUser(id);
        System.out.println(id+"\t"+DBConnection.importDBtoListForUser().size());
        if(id-1 != DBConnection.importDBtoListForUser().size())
            throw new RuntimeException();
        DBConnection.updateAIAll();

    }
}