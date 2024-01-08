import heritage.joined_table.CreditCardPayment;
import heritage.joined_table.PayPalPayment;
import heritage.single_table.CreditCardPaymentS;
import heritage.single_table.PayPalPaymentS;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            Transaction tx = session.getTransaction();
            tx.begin();
            //  ***************** exemple joined table :

         /*   CreditCardPayment creditCardPayment = new CreditCardPayment();
            creditCardPayment.setCardNumber("123456789");
            creditCardPayment.setAmount(2000.0);
            creditCardPayment.setPaymentDate(new Date());
            creditCardPayment.setExpirationDate("12/2025");

            PayPalPayment payPalPayment = new PayPalPayment();
            payPalPayment.setAccountNumber("125874");
            payPalPayment.setAmount(3000.0);
            payPalPayment.setPaymentDate(new Date());

            session.save(creditCardPayment);
            session.save(payPalPayment); */

            // ******************* exemple single table

            CreditCardPaymentS creditCardPayment = new CreditCardPaymentS();
            creditCardPayment.setCardNumber("123456789");
            creditCardPayment.setAmount(2000.0);
            creditCardPayment.setPaymentDate(new Date());
            creditCardPayment.setExpirationDate("12/2025");

            PayPalPaymentS payPalPayment = new PayPalPaymentS();
            payPalPayment.setAccountNumber("125874");
            payPalPayment.setAmount(3000.0);
            payPalPayment.setPaymentDate(new Date());

            session.save(creditCardPayment);
            session.save(payPalPayment);

            tx.commit();

        } catch (Exception ex) {


        } finally {
          session.close();
          sessionFactory.close();
        }


    }
}
