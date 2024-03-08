import javax.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    @OneToOne(mappedBy = "user")
    private Wallet wallet;

    public Users(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Users() {
    }

    public void getExchangeRates() {
        ExchangeRates.getExchangeRates();
    }

    public void transaction(String from, String to) {
        Transactions.transaction(Users.this, from, to);
    }

    public Double getAllSumInUAH() {
        return wallet.getUah() + (ExchangeRates.usdToUah(getWallet().getUsd()) + (ExchangeRates.eurToUah(getWallet().getEur())));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", wallet=" + wallet.getId() +
                '}';
    }
}
