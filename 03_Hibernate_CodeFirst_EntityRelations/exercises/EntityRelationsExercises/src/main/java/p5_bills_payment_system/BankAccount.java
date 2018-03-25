package p5_bills_payment_system;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

//@Entity(name = "bank_accounts")
public class BankAccount extends BillingDetail {

    private String bankName;

    private String SwiftCode;

    @Basic(optional = false)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(unique = true, nullable = false)
    public String getSwiftCode() {
        return SwiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        SwiftCode = swiftCode;
    }
}
