package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;

import java.util.List;

public interface PaymentService {
    Bill createBill(PaymentRequest request);
    Bill payRefund(Bill bill);
    Bill makePayment(Bill bill) throws InterruptedException;
    Bill approvePayment(Integer id);
    List<Bill> approveAllPayments();
    Bill getBillById(Integer id);
    List<Bill> getAllBills();
    List<Bill> getAllVerified();
    List<Bill> getAllNotVerified();
    Coupon addCoupon(CouponRequest request);
    Coupon getCouponByCode(String code);
    List<Coupon> getAllCoupons();
    Voucher addVoucher(VoucherRequest request);
    Voucher getVoucherByCode(String code);
    List<Voucher> getAllVouchers();

    List<Bill> getBillsByUsername(String john);
}
