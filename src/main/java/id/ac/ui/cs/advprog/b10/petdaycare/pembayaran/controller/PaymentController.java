package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path={"api/payment"})
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/customer/pay")
    public ResponseEntity<Bill> createPayment(@RequestBody PaymentRequest request) throws InterruptedException {
        Bill bill = paymentService.createBill(request);
        bill = paymentService.makePayment(bill);
        return ResponseEntity.ok(bill);
    }

    @PutMapping("/admin/approve/{id}")
    public ResponseEntity<Bill> approvePayment(@PathVariable String id){
        Bill bill = paymentService.approvePayment(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/admin/not-approved/")
    public ResponseEntity<List<Bill>> getAllNotAprove(){
        List<Bill> bills = paymentService.getAllNotApproved();
        return ResponseEntity.ok(bills);
    }
    @PostMapping("/admin/coupon/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody CouponRequest request) throws InterruptedException {
        Coupon coupon = paymentService.addCoupon(request);
        return ResponseEntity.ok(coupon);
    }

    @PostMapping("/admin/voucher/add")
    public ResponseEntity<Voucher> addVoucher(@RequestBody VoucherRequest request) throws InterruptedException {
        Voucher voucher = paymentService.addVoucher(request);
        return ResponseEntity.ok(voucher);
    }

}
