package clases;

import javax.persistence.*;

@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userID;
    private String surname;

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetails(int userID, String surname) {
        this.userID = userID;
        this.surname = surname;
    }

    public UserDetails(String surname) {
        this.surname = surname;
    }

    public UserDetails() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "clases.UserDetails{" +
                "userID=" + userID +
                ", surname='" + surname + '\'' +
                '}';
    }
}
