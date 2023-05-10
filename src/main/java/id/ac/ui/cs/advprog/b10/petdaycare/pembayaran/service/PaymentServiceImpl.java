package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.CodeGenerator;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.CouponAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.VoucherAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.PetWalletPayment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.CouponRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.BillRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final CouponRepository couponRepository;
    private final VoucherRepository voucherRepository;
    private final BillRepository billRepository;

    @Autowired
    public PaymentServiceImpl(CouponRepository couponRepository,
                              VoucherRepository voucherRepository,
                              BillRepository billRepository) {
        this.couponRepository = couponRepository;
        this.voucherRepository = voucherRepository;
        this.billRepository = billRepository;
    }

    public Bill createBill(PaymentRequest request) {
        var bill = Bill.builder()
                .idPenitipan(request.getIdPenitipan())
                .total(request.getTotal())
                .customerBalance(request.getCustomerBalance())
                .method(request.getMethod())
                .code(request.getCode())
                .build();

        billRepository.save(bill);
        return bill;
    }
    @Override
    public Bill makePayment(Bill bill) throws InterruptedException {
        String method = bill.getMethod();
        String code = bill.getCode();

        if (method.equals("voucher") && voucherRepository.existsByCode(code)) {
            VoucherAdapter voucherAdapter = new VoucherAdapter(voucherRepository.findByCode(code));
            return voucherAdapter.pay(bill);
        } else if (method.equals("coupon") && !code.equals("") && couponRepository.existsByCode(code)) {
            CouponAdapter couponAdapter = new CouponAdapter(couponRepository.findByCode(code));
            return couponAdapter.pay(bill);
        } else {
            PetWalletPayment petWalletPayment = new PetWalletPayment(bill);
            return petWalletPayment.pay(bill);
        }
    }

    @Override
    public Bill getBillById(String id) {
        Bill bill = null;
        if (billRepository.findById(id).isPresent()) {
            bill = billRepository.findById(id).get();
        }
        return bill;
    }

    @Override
    public Bill approvePayment(String id) {
        Bill bill = getBillById(id);
        bill.setVerified(true);
        return bill;
    }

    @Override
    public List<Bill> approveAllPayments() {
        List<Bill> bills = billRepository.findAllByVerified(false);
        for (Bill bill: bills) {
            bill.setVerified(true);
        }
        return bills;
    }

    @Override
    public List<Bill> getAllVerified() {
        List<Bill> bills = billRepository.findAllByVerified(true);
        return bills;
    }

    @Override
    public List<Bill> getAllNotVerified() {
        List<Bill> bills = billRepository.findAllByVerified(false);
        return bills;
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return bills;
    }

    @Override
    public Coupon addCoupon(CouponRequest request) {
        Coupon coupon = new Coupon();
        coupon.setCode(CodeGenerator.generate());
        coupon.setDiscount(request.getDiscount());
        couponRepository.save(coupon);
        return coupon;
    }

    @Override
    public Coupon getCouponByCode(String code) {
        return couponRepository.findByCode(code);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Voucher addVoucher(VoucherRequest request) {
        Voucher voucher = new Voucher();
        voucher.setCode(CodeGenerator.generate());
        voucher.setAmount(request.getAmount());
        voucherRepository.save(voucher);
        return voucher;
    }

    @Override
    public Voucher getVoucherByCode(String code) {
        return voucherRepository.findByCode(code);
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }
}

