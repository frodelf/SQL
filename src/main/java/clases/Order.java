package clases;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int id;
    @Column(name = "array_products")
    private String arrProducts;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "number_product")
    private int numberProduct;

    public Order(int id, String arrProducts, int userId, double totalPrice, int numberProduct) {
        this.id = id;
        this.arrProducts = arrProducts;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.numberProduct = numberProduct;
    }

    public Order(String arrProducts, int userId, double totalPrice, int numberProduct) {
        this.arrProducts = arrProducts;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.numberProduct = numberProduct;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArrProducts() {
        return arrProducts;
    }

    public void setArrProducts(String arrProducts) {
        this.arrProducts = arrProducts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        this.numberProduct = numberProduct;
    }

    @Override
    public String toString() {
        return "clases.Order{" +
                "id=" + id +
                ", arrProducts='" + arrProducts + '\'' +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", numberProduct=" + numberProduct +
                '}';
    }
}
