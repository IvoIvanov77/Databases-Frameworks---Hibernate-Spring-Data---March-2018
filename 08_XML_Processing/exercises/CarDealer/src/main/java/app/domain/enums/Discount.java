package app.domain.enums;

public enum Discount {
    DISCOUNT_00  (0.00f),
    DISCOUNT_05  (0.05f),
    DISCOUNT_10  (0.10f),
    DISCOUNT_15  (0.15f),
    DISCOUNT_20  (0.20f),
    DISCOUNT_30  (0.30f),
    DISCOUNT_40  (0.40f),
    DISCOUNT_50  (0.50f);

    private final float value;

    Discount(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
