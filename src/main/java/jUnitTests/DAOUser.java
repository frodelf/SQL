package jUnitTests;

import clases.User;
import clases.UserDetails;
import daoHibernate.HibernateUtil;
import daoJDBC.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;


class DAOUser {
    @Test
    public void getUsers() throws ClassNotFoundException {
        new DBConnection().dataBaseConnection();
        Session session = HibernateUtil.getSessionFactory().openSession();
        int size1 = daoHibernate.DAOUser.getUsers().size();
        int size2 = DBConnection.importDBtoListForUser().size();
        if(size1 != size2)throw new RuntimeException();
        session.close();
    }

    @Test
    public void getUserByID() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user1 = daoHibernate.DAOUser.getUserByID(1);
        User user2 = session.get(User.class, 1);
        if(!user1.equals(user2))throw new RuntimeException();
        session.close();
    }

    @Test
    public void saveUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int size1 = daoHibernate.DAOUser.getUsers().size();
        User user = new User("Irina", "Ir14a", 7000);
        UserDetails details = new UserDetails("II");
        daoHibernate.DAOUser.saveUser(user, details);
        int size2 = daoHibernate.DAOUser.getUsers().size();
        System.out.println(size1+"\t"+size2);
        if (size1 != --size2)throw new RuntimeException();
        int id = daoHibernate.DAOUser.getUsers().get(daoHibernate.DAOUser.getUsers().size()-1).getId();
        session.remove(session.get(UserDetails.class, id));
        session.remove(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Test
    public void removeUserByID() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        daoHibernate.DAOUser.saveUser(new User( "Remove", "Remove", 90), new UserDetails("RR"));
        int id = daoHibernate.DAOUser.getUsers().get(daoHibernate.DAOUser.getUsers().size()-1).getId();
        daoHibernate.DAOUser.removeUserByID(id);
        try {
            session.get(User.class, id);
            throw new RuntimeException();
        }catch (Exception e){}
        session.close();
    }

    @Test
    public void updateUserByID() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, 1);
        User userUp = new User("Update", "up", 100);
        daoHibernate.DAOUser.updateUserByID(userUp);
        if(session.get(User.class, 1).getName().equals("Update"))throw new RuntimeException();
        daoHibernate.DAOUser.updateUserByID(user);
        session.close();
    }

    @Test
    public void tearDown() throws ClassNotFoundException {
        DBConnection connection = new DBConnection();
        connection.dataBaseConnection();
        DBConnection.updateAIAll();
    }
}