package entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "ST")
public class Strawberry extends BasicIngredient {

    private static final BigDecimal PRICE = new BigDecimal(4.85);
    private static final String NAME = "Strawberry";

    public Strawberry() {
        super(NAME, PRICE);
    }
}
