package heritage.single_table;



import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name= "payment_single_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.INTEGER)
public abstract class PaymentS {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
