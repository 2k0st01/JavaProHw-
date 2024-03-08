import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
public class Wallet {

    @Id
    @GeneratedValue()
    private Long id;

    private Double uah = 500.0;
    private Double usd = 0.0;
    private Double eur = 0.0;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public Wallet() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getUah() {
        return uah;
    }

    public void addToUah(double uah) {
        this.uah += uah;
    }

    public void minusFromUah(double uah) {
        this.uah -= uah;
    }

    public Double getUsd() {
        return usd;
    }

    public void addToUsd(double usd) {
        this.usd += usd;
    }

    public void minusFromUsd(double usd) {
        this.usd -= usd;
    }

    public Double getEur() {
        return eur;
    }

    public void addToEur(double eur) {
        this.eur += eur;
    }

    public void minusFromEur(double eur) {
        this.eur -= eur;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
