package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue
    private Integer id;

    private String code;
    private double discount;
    private boolean isRedeemed;

    public synchronized Bill redeem(Bill bill) throws InterruptedException {
        if (!isRedeemed) {
//            Thread.sleep(3000);
            double customerBalance = bill.getCustomerBalance();
            double price = bill.getTotal();
            if (isEnoughBalance(customerBalance, price)) {
                isRedeemed = true;
                double finalPrice = price - (discount/100) * price;
                bill.setCustomerBalance(customerBalance - finalPrice);
                bill.setTotal(finalPrice);
                bill.setPaid(true);
            }
        }

        return bill;
    }

    public boolean isEnoughBalance(double customerBalance, double price) {
        return customerBalance >= (price - (discount/100) * price);
    }
}
