package jUnitTests;

import clases.User;
import clases.UserDetails;
import daoHibernate.*;
import daoHibernate.DAOUser;
import daoJDBC.DBConnection;
import daoJDBC.TableName;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    @Test
    void buyProductByUserID() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        DBConnection.updateAIAll();
        User user = new User("DDD", "ddd", 12500);
        UserDetails details = new UserDetails("Dd");
        DAOUser.saveUser(user, details);
        int idUser = DBConnection.importDBtoListForUser().get(DBConnection.importDBtoListForUser().size()-1).getId();
        DAOShoppingCart.addProductToUsersCartByID(idUser, 1);
        DAOShoppingCart.addProductToUsersCartByID(idUser, 2);

        int size1 = DBConnection.valueDB(TableName.orders);

        OrderService.buyProductByUserID(idUser);

        int sizeProducts2 = DAOUser.getUserByID(idUser).getProducts().size();
        int sizeForOrder2 = DBConnection.importDBtoListForOrders(idUser).get(DBConnection.importDBtoListForOrders(idUser).size()-1).getId();

        if(sizeProducts2 != 0  ||  size1+1 != sizeForOrder2) {
            throw new RuntimeException();
        }

//        Assert.


        DAOOrders.removeOrderByID(sizeForOrder2);
        DAOUser.removeUserByID(idUser);

        DBConnection.updateAIAll();
    }
}