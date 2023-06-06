package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.CodeGenerator;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.CouponAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.VoucherAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.PetWalletPayment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.PaymentMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.CouponRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.BillRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.VoucherRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final CouponRepository couponRepository;
    private final VoucherRepository voucherRepository;
    private final BillRepository billRepository;

    private final CustomerService customerService;

    @Autowired
    public PaymentServiceImpl(CouponRepository couponRepository,
                              VoucherRepository voucherRepository,
                              BillRepository billRepository,
                              CustomerService customerService) {
        this.couponRepository = couponRepository;
        this.voucherRepository = voucherRepository;
        this.billRepository = billRepository;
        this.customerService = customerService;
    }

    public Bill createBill(PaymentRequest request) {
        if(request.getUsername() == null) throw new InvalidInputException();
        if(request.getToken() == null) throw new InvalidInputException();
        if(request.getIdPenitipan() == null) throw new InvalidInputException();

        Customer findCustomer = customerService.findCustomer(request.getUsername());
        if(findCustomer == null){
            findCustomer = customerService.createCustomer(new CustomerRequest(request.getUsername(), request.getToken()));

            var bill = Bill.builder()
                    .idCustomer(findCustomer.getCustomerId())
                    .username(findCustomer.getUsername())
                    .idPenitipan(request.getIdPenitipan())
                    .total(request.getTotal())
                    .customerBalance(findCustomer.getBalance())
                    .method(PaymentMethod.valueOf(request.getMethod()))
                    .code(request.getCode())
                    .build();

            billRepository.save(bill);
            return bill;
        }

        return getBill(request, findCustomer);
    }

    @Override
    public Bill payRefund(Bill bill) {
        Double total = bill.getTotal();
        // total in negative amount
        bill.setCustomerBalance(bill.getCustomerBalance() - total);
        bill.setPaid(true);
        bill.setVerified(true);
        return bill;
    }

    private Bill getBill(PaymentRequest request, Customer findCustomer) {
        var bill = Bill.builder()
                .idCustomer(findCustomer.getCustomerId())
                .username(findCustomer.getUsername())
                .idPenitipan(request.getIdPenitipan())
                .total(request.getTotal())
                .customerBalance(findCustomer.getBalance())
                .method(PaymentMethod.valueOf(request.getMethod()))
                .code(request.getCode())
                .build();

        billRepository.save(bill);
        addBillToCustomer(findCustomer, bill);

        return bill;
    }

    @Override
    public Bill makePayment(Bill bill) throws InterruptedException {
        PaymentMethod method = bill.getMethod();
        String code = bill.getCode();

        if (method.equals(PaymentMethod.PET_WALLET_WITH_VOUCHER) && voucherRepository.existsByCode(code)) {
            VoucherAdapter voucherAdapter = new VoucherAdapter(voucherRepository.findByCode(code), customerService);
            return voucherAdapter.pay(bill);
        } else if (method.equals(PaymentMethod.PET_WALLET_WITH_COUPON) && !code.equals("") && couponRepository.existsByCode(code)) {
            CouponAdapter couponAdapter = new CouponAdapter(couponRepository.findByCode(code), customerService);
            return couponAdapter.pay(bill);
        } else if (method.equals(PaymentMethod.PET_WALLET)){
            PetWalletPayment petWalletPayment = new PetWalletPayment(bill, customerService);
            return petWalletPayment.pay(bill);
        } else {
            return bill;
        }
    }

    @Override
    public Bill getBillById(Integer id) {
        Bill bill = billRepository.findBillById(id);
        if (bill == null) {
            throw new BillDoesNotExistException(id);
        }
        return bill;
    }

    @Override
    public Bill approvePayment(Integer id) {
        try {
            Bill bill = getBillById(id); // Retrieve the bill by ID

            // Update the bill's verification status
            bill.setVerified(true);

            return bill;
        } catch (BillDoesNotExistException ex) {
            // Handle the case when the bill does not exist
            throw new PaymentApprovalException(id);
        }
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
        return billRepository.findAllByVerified(true);
    }

    @Override
    public List<Bill> getAllNotVerified() {
        return billRepository.findAllByVerified(false);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();

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
        Coupon coupon = couponRepository.findByCode(code);
        if (coupon == null) {
            throw new CouponDoesNotExistException(code);
        }
        return coupon;
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
        Voucher voucher = voucherRepository.findByCode(code);
        if (voucher == null) {
            throw new VoucherDoesNotExistException(code);
        }
        return voucher;
    }


    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public List<Bill> getBillsByUsername(String john) {
        return billRepository.findAllByUsername(john);
    }


    public boolean addBillToCustomer(Customer customer, Bill bill) {
        List<Bill> customerListPayment = customer.getPaymentList();
        if (customerListPayment == null) {
            customerListPayment = new ArrayList<>();
        }

        customerListPayment.add(bill);
        customer.setPaymentList(customerListPayment);

        return customer.getPaymentList().contains(bill);
    }
}

