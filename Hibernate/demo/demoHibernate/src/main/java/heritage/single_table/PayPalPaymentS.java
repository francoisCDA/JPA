package heritage.single_table;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class PayPalPaymentS extends PaymentS {

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
