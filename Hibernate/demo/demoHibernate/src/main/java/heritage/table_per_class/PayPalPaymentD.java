package heritage.table_per_class;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="paypal_payment")
public class PayPalPaymentD extends PaymentD {

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
