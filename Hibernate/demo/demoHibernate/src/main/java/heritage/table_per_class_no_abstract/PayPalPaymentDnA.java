package heritage.table_per_class_no_abstract;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="paypal_payment_no_abst")
public class PayPalPaymentDnA extends PaymentDnA {

    private String accountNumber;


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "PayPalPayment : " + super.toString() +
                ", accountNumber='" + accountNumber + '\'' +
                '.';
    }
}
