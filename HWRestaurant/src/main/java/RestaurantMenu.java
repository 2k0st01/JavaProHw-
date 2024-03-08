import javax.persistence.*;

@Entity
@Table
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "dish")
    private String nameOfTheDish;
    private int discount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int weight;

    public RestaurantMenu(String nameOfTheDish, int discount, double price, int weight) {
        this.nameOfTheDish = nameOfTheDish;
        this.discount = discount;
        this.price = price;
        this.weight = weight;
    }

    public RestaurantMenu() {

    }

    public String getNameOfTheDish() {
        return nameOfTheDish;
    }

    public void setNameOfTheDish(String nameOfTheDish) {
        this.nameOfTheDish = nameOfTheDish;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RestaurantMenu{" +
                "id=" + id +
                ", nameOfTheDish='" + nameOfTheDish + '\'' +
                ", discount=" + discount + "%" +
                ", price=" + price + "$" +
                ", weight=" + weight + " mg" +
                '}';
    }
}
