package heritage.table_per_class_no_abstract;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card_payement_no_abst")
public class CreditCardPaymentDnA extends PaymentDnA {

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
