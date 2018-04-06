package app.domain.models;

import app.domain.enums.Discount;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

//    •	Sale has car, customer and discount percentage

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Discount discount;


    /*
    right way to create one One-to-one association that maps a foreign key column
    https://docs.jboss.org/hibernate/jpa/2.1/api/javax/persistence/OneToOne.html
    */
    @OneToOne(optional=false)
    @JoinColumn(
            name="car_id", unique=true, nullable=false, updatable=false)
    private Car car;

    @ManyToOne
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
