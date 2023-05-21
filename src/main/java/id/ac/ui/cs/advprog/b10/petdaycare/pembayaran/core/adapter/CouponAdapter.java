package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.Payment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;

public class CouponAdapter implements Payment {
    private final Coupon adaptedCoupon;

    public CouponAdapter(Coupon coupon) {
        this.adaptedCoupon = coupon;
    }

    @Override
    public Bill pay(Bill bill) throws InterruptedException {;
        return adaptedCoupon.redeem(bill);
    }
}
