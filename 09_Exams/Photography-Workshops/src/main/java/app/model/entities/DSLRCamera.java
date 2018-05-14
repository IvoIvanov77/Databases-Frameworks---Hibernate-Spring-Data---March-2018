package app.model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DSLR")
public class DSLRCamera extends BasicCamera{

    private final static String type = "DSLR";

    private Integer maxShutterSpeed;

    public DSLRCamera() {
    }

    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }
}
