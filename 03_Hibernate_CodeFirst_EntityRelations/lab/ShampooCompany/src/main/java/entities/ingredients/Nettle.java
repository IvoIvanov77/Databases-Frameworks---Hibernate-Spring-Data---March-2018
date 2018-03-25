package entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "NE")
public class Nettle extends BasicIngredient {

    private static final BigDecimal PRICE = new BigDecimal(6.12);
    private static final String NETTLE = "Nettle";

    public Nettle() {
        super(NETTLE, PRICE);
    }
}
