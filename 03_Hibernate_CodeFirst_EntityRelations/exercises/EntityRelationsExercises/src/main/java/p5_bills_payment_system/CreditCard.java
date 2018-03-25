package p5_bills_payment_system;

import javax.persistence.Basic;
import javax.persistence.Entity;

//@Entity(name = "credit_cards")
public class CreditCard  extends BillingDetail{

    private String cardType;

    private int expirationMonth;

    private int expirationYear;

    @Basic(optional = false)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Basic(optional = false)
    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Basic(optional = false)
    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
