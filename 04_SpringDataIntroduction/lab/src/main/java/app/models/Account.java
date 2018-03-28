package app.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "accounts")
public class Account {

    private long id;

    private BigDecimal balance;

    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "balance", nullable = false)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if(balance.doubleValue() < 0){
            throw new IllegalArgumentException("balance can't be negative");
        }
        this.balance = balance;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
