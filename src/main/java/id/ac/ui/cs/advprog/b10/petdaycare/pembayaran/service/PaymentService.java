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
    List<Bill> approveAllPayments();
    Bill getBillById(String id);
    List<Bill> getAllBills();
    List<Bill> getAllVerified();
    List<Bill> getAllNotVerified();
    Coupon addCoupon(CouponRequest request);
    Coupon getCouponByCode(String code);
    List<Coupon> getAllCoupons();
    Voucher addVoucher(VoucherRequest request);
    Voucher getVoucherByCode(String code);
    List<Voucher> getAllVouchers();

}
