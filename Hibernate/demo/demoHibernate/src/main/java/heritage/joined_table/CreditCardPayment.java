package heritage.joined_table;

import javax.persistence.*;

@Entity
@Table(name="credit_card")
@PrimaryKeyJoinColumn(name="idPayment")
public class CreditCardPayment extends Payment {

    private String cardNumber;

    private String expirationDate;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "CreditCardPayment : " + super.toString() +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '.';
    }
}
