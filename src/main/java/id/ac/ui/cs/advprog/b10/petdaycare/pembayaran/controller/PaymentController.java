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

    @PutMapping("/admin/approve-all")
    public ResponseEntity<List<Bill>> approveAllPayments(){
        List<Bill> bills = paymentService.approveAllPayments();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/admin/approved/")
    public ResponseEntity<List<Bill>> getAllVerified(){
        List<Bill> bills = paymentService.getAllVerified();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/admin/not-approved")
    public ResponseEntity<List<Bill>> getAllNotVerified(){
        List<Bill> bills = paymentService.getAllNotVerified();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/admin/bill/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable String id){
        Bill bill = paymentService.getBillById(id);
        return ResponseEntity.ok(bill);
    }
    @GetMapping("/admin/all-bills")
    public ResponseEntity<List<Bill>> getAllBills(){
        List<Bill> bills = paymentService.getAllBills();
        return ResponseEntity.ok(bills);
    }
    @PostMapping("/admin/coupon/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody CouponRequest request) throws InterruptedException {
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
    public ResponseEntity<Voucher> addVoucher(@RequestBody VoucherRequest request) throws InterruptedException {
        Voucher voucher = paymentService.addVoucher(request);
        return ResponseEntity.ok(voucher);
    }

    @GetMapping("/voucher/{code}")
    public ResponseEntity<Voucher> getVouchernByCode(@PathVariable String code){
        return ResponseEntity.ok(paymentService.getVoucherByCode(code));
    }

    @GetMapping("/vouchers")
    public ResponseEntity<List<Voucher>> getAllVouchers(){
        return ResponseEntity.ok(paymentService.getAllVouchers());
    }

}
