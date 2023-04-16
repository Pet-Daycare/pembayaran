package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Coupon {
    private String id;
    private double discount;
    private boolean isRedeemed;

    public Coupon(double discount) {
        this.discount = discount;
    }

    public synchronized double redeem(double price) throws InterruptedException {
        if (!isRedeemed) {
            Thread.sleep(3000);
            isRedeemed = true;
            return price - (discount/100) * price;
        }

        return -1;
    }
}