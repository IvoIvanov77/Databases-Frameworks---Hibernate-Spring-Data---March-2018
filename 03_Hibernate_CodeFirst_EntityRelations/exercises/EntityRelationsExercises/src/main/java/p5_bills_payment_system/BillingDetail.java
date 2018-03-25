package p5_bills_payment_system;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail {

    private long number;

    private User owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @ManyToOne(optional = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
