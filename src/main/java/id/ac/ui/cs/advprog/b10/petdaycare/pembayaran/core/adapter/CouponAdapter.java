package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.Payment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;

public class CouponAdapter implements Payment {
    private final Coupon adaptedCoupon;

    private final CustomerService customerService;

    public CouponAdapter(Coupon coupon, CustomerService customerService) {
        this.adaptedCoupon = coupon;
        this.customerService = customerService;
    }

    @Override
    public Bill pay(Bill bill) throws InterruptedException {
        if(adaptedCoupon.isRedeemed()){
            return bill;
        }
        return adaptedCoupon.redeem(bill, customerService);
    }
}
