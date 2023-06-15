package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"api/coupon/"})
@RequiredArgsConstructor
public class CouponController {

    private final PaymentService paymentService;
    @PostMapping("/admin/coupon/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody CouponRequest request) {
        Coupon coupon = paymentService.addCoupon(request);
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/coupon/{code}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code){
        return ResponseEntity.ok(paymentService.getCouponByCode(code));
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        return ResponseEntity.ok(paymentService.getAllCoupons());
    }

    @PostMapping("/admin/voucher/add")
    public ResponseEntity<Voucher> addVoucher(@RequestBody VoucherRequest request) {
        Voucher voucher = paymentService.addVoucher(request);
        return ResponseEntity.ok(voucher);
    }

    @GetMapping("/voucher/{code}")
    public ResponseEntity<Voucher> getVoucherByCode(@PathVariable String code){
        return ResponseEntity.ok(paymentService.getVoucherByCode(code));
    }

    @GetMapping("/vouchers")
    public ResponseEntity<List<Voucher>> getAllVouchers(){
        return ResponseEntity.ok(paymentService.getAllVouchers());
    }
}
