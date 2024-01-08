package heritage.joined_table;

import javax.persistence.*;

@Entity
@Table(name = "paypal")
@PrimaryKeyJoinColumn(name="idPayment")
public class PayPalPayment extends Payment {

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
