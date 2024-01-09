package heritage.table_per_class;



import javax.persistence.*;
import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentD {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPayment;

    private Double Amount;

    private Date paymentDate = new Date();


    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return  " idPayment=" + idPayment +
                ", Amount=" + Amount +
                ", paymentDate=" + paymentDate +
                ';';
    }

}
