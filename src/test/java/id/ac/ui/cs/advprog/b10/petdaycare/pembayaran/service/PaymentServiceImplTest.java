package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.CodeGenerator;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.CouponAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.VoucherAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponVoucher.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponVoucher.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.PetWalletPayment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.InvalidInputException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.PaymentMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.CouponRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.BillRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.VoucherRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentServiceImplTest {

    @MockBean
    private CouponRepository couponRepository;

    @MockBean
    private VoucherRepository voucherRepository;

    @MockBean
    private BillRepository billRepository;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBill_WithValidRequest_ShouldCreateBill() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        request.setUsername("john_doe");
        request.setToken("123456");
        request.setIdPenitipan(1);
        request.setTotal(100.0);
        request.setMethod("PET_WALLET");
        request.setCode("ABC123");

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setUsername("john_doe");
        customer.setBalance(200.0);

        when(customerService.findCustomer("john_doe")).thenReturn(customer);

        // Act
        Bill bill = paymentService.createBill(request);

        // Assert
        assertNotNull(bill);
        assertEquals("john_doe", bill.getUsername());
        assertEquals(1, bill.getIdPenitipan());
        assertEquals(100.0, bill.getTotal());
        assertEquals(PaymentMethod.PET_WALLET, bill.getMethod());
        assertEquals("ABC123", bill.getCode());
        assertFalse(bill.isVerified());
        assertEquals(customer.getCustomerId(), bill.getIdCustomer());
        assertEquals(customer.getBalance(), bill.getCustomerBalance());
        verify(billRepository, times(1)).save(bill);
        verify(customerService, never()).createCustomer(any());
        verify(customerService, times(1)).findCustomer("john_doe");
        verify(customerService, never()).setBalance(any(), anyDouble());
    }

    @Test
    public void testCreateBill_WithUnknownCustomer_ShouldCreateCustomerAndBill() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        request.setUsername("john_doe");
        request.setToken("123456");
        request.setIdPenitipan(1);
        request.setTotal(100.0);
        request.setMethod("PET_WALLET");
        request.setCode("ABC123");

        when(customerService.findCustomer("john_doe")).thenReturn(null);

        // Act
        Bill bill = paymentService.createBill(request);

        // Assert
        assertNotNull(bill);
        assertEquals("john_doe", bill.getUsername());
        assertEquals(1, bill.getIdPenitipan());
        assertEquals(100.0, bill.getTotal());
        assertEquals(PaymentMethod.PET_WALLET, bill.getMethod());
        assertEquals("ABC123", bill.getCode());
        assertFalse(bill.isVerified());
        assertNull(bill.getIdCustomer());
        assertEquals(0.0, bill.getCustomerBalance());
        verify(billRepository, times(1)).save(bill);
//        verify(customerService, times(1)).createCustomer(any());
        verify(customerService, times(1)).findCustomer("john_doe");
        verify(customerService, never()).setBalance(any(), anyDouble());
    }

    @Test
    public void testCreateBill_WithMissingUsername_ShouldThrowInvalidInputException() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        request.setToken("123456");
        request.setIdPenitipan(1);
        request.setTotal(100.0);
        request.setMethod("PET_WALLET");
        request.setCode("ABC123");

        // Act and Assert
        assertThrows(InvalidInputException.class, () -> paymentService.createBill(request));
        verify(billRepository, never()).save(any());
        verify(customerService, never()).findCustomer(any());
        verify(customerService, never()).createCustomer(any());
        verify(customerService, never()).setBalance(any(), anyDouble());
    }

    // Other test cases for the methods in PaymentServiceImpl can be added here...
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