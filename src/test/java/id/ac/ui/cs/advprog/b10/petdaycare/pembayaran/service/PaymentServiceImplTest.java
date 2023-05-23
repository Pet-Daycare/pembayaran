package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.PaymentMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.BillRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.CouponRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.VoucherRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {
    @Mock
    private BillRepository billRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private VoucherRepository voucherRepository;


    @InjectMocks
    private PaymentServiceImpl paymentService;

    Coupon couponDisc50;
    Voucher voucherAmount150;
    Bill userWithPetWallet;
    Bill userWithCoupon;

    Bill userWithVoucher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

//        couponDisc50 = Coupon.builder()
//                .code("coupon50")
//                .discount(50)
//                .build();
//
//        voucherAmount150 = Voucher.builder()
//                .code("voucher150")
//                .amount(150000)
//                .build();
//
//        userWithPetWallet = Bill.builder()
//                .idCustomer(1)
//                .username("katniss")
//                .idPenitipan(1)
//                .total(150000)
//                .customerBalance(200000)
//                .method(PaymentMethod.PET_WALLET)
//                .code("")
//                .build();
//
//        userWithCoupon = Bill.builder()
//                .idCustomer(2)
//                .username("georgia")
//                .idPenitipan(2)
//                .total(140000)
//                .customerBalance(100000)
//                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
//                .code("coupon50")
//                .build();
//
//        userWithVoucher = Bill.builder()
//                .idCustomer(3)
//                .username("sheldon")
//                .idPenitipan(3)
//                .total(150000)
//                .customerBalance(100000)
//                .method(PaymentMethod.PET_WALLET_WITH_VOUCHER)
//                .code("voucher150")
//                .build();
    }

    @Test
    void testGetAllBills() {
        List<Bill> bills = new ArrayList<>();

        bills.add(new Bill(1, "katniss", 1, 1, 150000, 160000, PaymentMethod.PET_WALLET, "", false, false));
        bills.add(new Bill(2, "liam", 2, 2, 100000, 90000, PaymentMethod.PET_WALLET_WITH_VOUCHER, "voucher1",true, false));

        when(billRepository.findAll()).thenReturn(bills);
        List<Bill> actualBills = paymentService.getAllBills();

        verify(billRepository, times(1)).findAll();
        assertEquals(2, actualBills.size());
    }

    @Test
    void testFindBillById() {
        int id = 1;
        Bill expectedBill = new Bill(id, "lori", 1, 1, 150000, 160000, PaymentMethod.PET_WALLET, "", false, false);

        when(billRepository.findBillById(id)).thenReturn(expectedBill);

        Bill actualBill = paymentService.getBillById(id);

        verify(billRepository, times(1)).findBillById(id);
        assertEquals(expectedBill, actualBill);
    }
    @Test
    void testGetAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        String code1 = "coupon1";
        String code2 = "coupon2";
        coupons.add(new Coupon(1, code1, 20, true));
        coupons.add(new Coupon(2, code2, 80, false));

        when(couponRepository.findAll()).thenReturn(coupons);
        List<Coupon> actualCoupons = paymentService.getAllCoupons();

        verify(couponRepository, times(1)).findAll();
        assertEquals(2, actualCoupons.size());

    }
    @Test
    void testFindCouponByCode() {
        String code = "coupon1";
        Coupon expectedCoupon = new Coupon(1, code, 50, false);

        when(couponRepository.findByCode(code)).thenReturn(expectedCoupon);

        Coupon actualCoupon = paymentService.getCouponByCode(code);

        verify(couponRepository, times(1)).findByCode(code);
        assertEquals(expectedCoupon, actualCoupon);
    }

    @Test
    void testGetAllVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String code1 = "voucher1";
        String code2 = "voucher2";
        vouchers.add(new Voucher(1, code1, 12000, true));
        vouchers.add(new Voucher(2, code2, 80000, false));

        when(voucherRepository.findAll()).thenReturn(vouchers);
        List<Voucher> actualvouchers = paymentService.getAllVouchers();

        verify(voucherRepository, times(1)).findAll();
        assertEquals(2, actualvouchers.size());

    }
    @Test
    void testFindVoucherByCode() {
        String code = "voucher1";
        Voucher expectedVoucher = new Voucher(1, code, 150000, false);

        when(voucherRepository.findByCode(code)).thenReturn(expectedVoucher);
        Voucher actualVoucher = paymentService.getVoucherByCode(code);

        verify(voucherRepository, times(1)).findByCode(code);
        assertEquals(expectedVoucher, actualVoucher);
    }


}
