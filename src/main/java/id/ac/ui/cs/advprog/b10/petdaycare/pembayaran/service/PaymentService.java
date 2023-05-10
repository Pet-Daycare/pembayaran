package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Voucher;

import java.util.List;

public interface PaymentService {
    Bill createBill(PaymentRequest request);
    Bill makePayment(Bill bill) throws InterruptedException;
    Bill approvePayment(String id);
    List<Bill> getAllNotApproved();
    Coupon addCoupon(CouponRequest request);
    Voucher addVoucher(VoucherRequest request);

}
