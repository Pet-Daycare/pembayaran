package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.PetWalletPayment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.PaymentMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.CustomerRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.BillRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.CouponRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.VoucherRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    private PaymentServiceImpl paymentService;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private BillRepository billRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    PetWalletPayment petWalletPayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentService = new PaymentServiceImpl(couponRepository, voucherRepository, billRepository, customerService);
    }

    @Test
    void createBill_withValidRequest_shouldReturnBill() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUsername("john");
        paymentRequest.setToken("123456");
        paymentRequest.setIdPenitipan(1);
        paymentRequest.setTotal(100.0);
        paymentRequest.setMethod("PET_WALLET");
        paymentRequest.setCode("ABC123");

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setUsername("john");
        customer.setBalance(200.0);

        when(customerService.findCustomer("john")).thenReturn(customer);
        when(billRepository.save(any(Bill.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Bill bill = paymentService.createBill(paymentRequest);

        assertNotNull(bill);
        assertEquals("john", bill.getUsername());
        assertEquals(1, bill.getIdPenitipan());
        assertEquals(100.0, bill.getTotal());
        assertEquals(200.0, bill.getCustomerBalance());
        assertEquals(PaymentMethod.PET_WALLET, bill.getMethod());
        assertEquals("ABC123", bill.getCode());
        assertFalse(bill.isVerified());

        verify(customerService, times(1)).findCustomer("john");
        verify(billRepository, times(1)).save(any(Bill.class));
    }


    @Test
    void payRefund_ValidBill_ReturnsPaidRefundedBill() {
        // Arrange
        Bill bill = new Bill();
        bill.setTotal(-100.0);
        bill.setCustomerBalance(200.0);
        bill.setPaid(false);
        bill.setVerified(false);

        // Act
        Bill result = paymentService.payRefund(bill);

        // Assert
        assertTrue(result.isPaid());
        assertTrue(result.isVerified());
        assertEquals(300.0, result.getCustomerBalance(), 0.01);
    }

    @Test
    void makePayment_withNotValidCouponPayment_shouldReturnUnpaidBill() throws InterruptedException {
        Customer customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(500000.0);
        customerRepository.save(customer);

        Bill bill = new Bill();
        bill.setId(1);
        bill.setUsername("john");
        bill.setMethod(PaymentMethod.PET_WALLET_WITH_COUPON);
        bill.setCode("ABC123");
        bill.setTotal(100000.0);
        billRepository.save(bill);

        when(customerService.findCustomer("john")).thenReturn(customer);
        when(couponRepository.existsByCode("ABC123")).thenReturn(false);

        Bill result = paymentService.makePayment(bill);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("john", result.getUsername());
        assertEquals(PaymentMethod.PET_WALLET_WITH_COUPON, result.getMethod());
        assertEquals("ABC123", result.getCode());
        assertFalse(result.isPaid());

        verify(billRepository, times(1)).save(any(Bill.class));
    }
    @Test
    void makePayment_withVoucherPayment_shouldReturnPaidBill() throws InterruptedException {
        Customer customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(500000.0);
        customerRepository.save(customer);

        Bill bill = new Bill();
        bill.setId(1);
        bill.setUsername("john");
        bill.setMethod(PaymentMethod.PET_WALLET_WITH_VOUCHER);
        bill.setCode("ABC123");
        bill.setTotal(100.0);
        billRepository.save(bill);

        Voucher voucher = new Voucher();
        voucher.setId(1);
        voucher.setCode("ABC123");
        voucher.setAmount(100.0);
        voucher.setRedeemed(false);
        voucherRepository.save(voucher);

        when(customerService.findCustomer("john")).thenReturn(customer);
        when(voucherRepository.existsByCode("ABC123")).thenReturn(true);
        when(voucherRepository.findByCode("ABC123")).thenReturn(voucher);

        Bill result = paymentService.makePayment(bill);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("john", result.getUsername());
        assertEquals(PaymentMethod.PET_WALLET_WITH_VOUCHER, result.getMethod());
        assertEquals("ABC123", result.getCode());
        assertTrue(result.isPaid());

        verify(voucherRepository, times(1)).existsByCode("ABC123");
        verify(voucherRepository, times(1)).findByCode("ABC123");
    }

    @Test
    void makePayment_withPetWalletPayment_shouldReturnPaidBill() throws InterruptedException {
        Customer customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(500000.0);
        customerRepository.save(customer);

        Bill bill = new Bill();
        bill.setId(1);
        bill.setUsername("john");
        bill.setTotal(100000.0);
        bill.setMethod(PaymentMethod.PET_WALLET);
        bill.setCode("");
        billRepository.save(bill);

        when(customerService.findCustomer("john")).thenReturn(customer);

        Bill result = paymentService.makePayment(bill);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("john", result.getUsername());
        assertEquals(PaymentMethod.PET_WALLET, result.getMethod());
        assertTrue(result.isPaid());

        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void makePayment_withCouponPayment_shouldReturnPaidBill() throws InterruptedException {
        Customer customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(500000.0);
        customerRepository.save(customer);

        Coupon coupon = new Coupon();
        coupon.setId(1);
        coupon.setCode("ABC123");
        coupon.setDiscount(50);
        couponRepository.save(coupon);

        Bill bill = new Bill();
        bill.setId(1);
        bill.setUsername("john");
        bill.setMethod(PaymentMethod.PET_WALLET_WITH_COUPON);
        bill.setCode("ABC123");
        bill.setTotal(100000.0);
        billRepository.save(bill);

        when(customerService.findCustomer("john")).thenReturn(customer);
        when(couponRepository.existsByCode("ABC123")).thenReturn(true);
        when(couponRepository.findByCode("ABC123")).thenReturn(coupon);

        Bill result = paymentService.makePayment(bill);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("john", result.getUsername());
        assertEquals(PaymentMethod.PET_WALLET_WITH_COUPON, result.getMethod());
        assertEquals("ABC123", result.getCode());
        assertTrue(result.isPaid());

        verify(couponRepository, times(1)).existsByCode("ABC123");
        verify(couponRepository, times(1)).findByCode("ABC123");
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void getBillById_withValidId_shouldReturnBill() {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setUsername("john");
        bill.setMethod(PaymentMethod.PET_WALLET);
        bill.setCode("ABC123");

        when(billRepository.findBillById(1)).thenReturn(bill);

        Bill result = paymentService.getBillById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("john", result.getUsername());
        assertEquals(PaymentMethod.PET_WALLET, result.getMethod());
        assertEquals("ABC123", result.getCode());

        verify(billRepository, times(1)).findBillById(1);
    }

    @Test
    void approvePayment_withValidId_shouldReturnApprovedBill() {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setUsername("john");
        bill.setMethod(PaymentMethod.PET_WALLET);
        bill.setCode("ABC123");
        bill.setVerified(false);

        when(billRepository.findBillById(1)).thenReturn(bill);
        when(billRepository.save(any(Bill.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Bill result = paymentService.approvePayment(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("john", result.getUsername());
        assertEquals(PaymentMethod.PET_WALLET, result.getMethod());
        assertEquals("ABC123", result.getCode());
        assertTrue(result.isVerified());

        verify(billRepository, times(1)).findBillById(1);
    }

    @Test
    void approveAllPayments_withUnverifiedBills_shouldReturnApprovedBills() {
        Bill bill1 = new Bill();
        bill1.setId(1);
        bill1.setUsername("john");
        bill1.setMethod(PaymentMethod.PET_WALLET);
        bill1.setCode("ABC123");
        bill1.setVerified(false);

        Bill bill2 = new Bill();
        bill2.setId(2);
        bill2.setUsername("mary");
        bill2.setMethod(PaymentMethod.PET_WALLET);
        bill2.setCode("DEF456");
        bill2.setVerified(false);

        when(billRepository.findAllByVerified(false)).thenReturn(List.of(bill1, bill2));
        when(billRepository.save(any(Bill.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Bill> result = paymentService.approveAllPayments();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(Bill::isVerified));
    }

    @Test
    void getAllVerified_withVerifiedBills_shouldReturnVerifiedBills() {
        Bill bill1 = new Bill();
        bill1.setId(1);
        bill1.setUsername("john");
        bill1.setMethod(PaymentMethod.PET_WALLET);
        bill1.setCode("ABC123");
        bill1.setVerified(true);

        Bill bill2 = new Bill();
        bill2.setId(2);
        bill2.setUsername("mary");
        bill2.setMethod(PaymentMethod.PET_WALLET);
        bill2.setCode("DEF456");
        bill2.setVerified(true);

        when(billRepository.findAllByVerified(true)).thenReturn(List.of(bill1, bill2));

        List<Bill> result = paymentService.getAllVerified();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(Bill::isVerified));

        verify(billRepository, times(1)).findAllByVerified(true);
    }

    @Test
    void getAllNotVerified_withUnverifiedBills_shouldReturnUnverifiedBills() {
        Bill bill1 = new Bill();
        bill1.setId(1);
        bill1.setUsername("john");
        bill1.setMethod(PaymentMethod.PET_WALLET);
        bill1.setCode("ABC123");
        bill1.setVerified(false);

        Bill bill2 = new Bill();
        bill2.setId(2);
        bill2.setUsername("mary");
        bill2.setMethod(PaymentMethod.PET_WALLET);
        bill2.setCode("DEF456");
        bill2.setVerified(false);

        when(billRepository.findAllByVerified(false)).thenReturn(List.of(bill1, bill2));

        List<Bill> result = paymentService.getAllNotVerified();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(result.stream().anyMatch(Bill::isVerified));

        verify(billRepository, times(1)).findAllByVerified(false);
    }

    @Test
    void getBillsByUsername_withValidUsername_shouldReturnBills() {
        Bill bill1 = new Bill();
        bill1.setId(1);
        bill1.setUsername("john");
        bill1.setMethod(PaymentMethod.PET_WALLET);
        bill1.setCode("ABC123");
        bill1.setVerified(true);

        Bill bill2 = new Bill();
        bill2.setId(2);
        bill2.setUsername("john");
        bill2.setMethod(PaymentMethod.PET_WALLET);
        bill2.setCode("DEF456");
        bill2.setVerified(true);

        when(billRepository.findAllByUsername("john")).thenReturn(List.of(bill1, bill2));

        List<Bill> result = paymentService.getBillsByUsername("john");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(bill -> bill.getUsername().equals("john")));

        verify(billRepository, times(1)).findAllByUsername("john");
    }



    @Test
    void testCreateBill_WithValidRequest_ShouldCreateBill() {
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
    void testCreateBill_WithMissingUsername_ShouldThrowInvalidInputException() {
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
    void getBillById_ValidId_ReturnsBill() {
        // Arrange
        Integer billId = 123;
        Bill expectedBill = new Bill(billId, "karin", 1, 1, 120000, 120000, PaymentMethod.PET_WALLET, "", false, false);

        when(billRepository.findBillById(billId)).thenReturn(expectedBill);

        // Act
        Bill actualBill = paymentService.getBillById(billId);

        // Assert
        assertNotNull(actualBill);
        assertEquals(expectedBill, actualBill);
        verify(billRepository, times(1)).findBillById(billId);
    }

    @Test
    void getBillById_InvalidId_ThrowsBillDoesNotExistException() {
        // Arrange
        Integer billId = 456;

        when(billRepository.findBillById(billId)).thenReturn(null);

        // Act & Assert
        assertThrows(BillDoesNotExistException.class, () -> {
            paymentService.getBillById(billId);
        }, "Expected BillDoesNotExistException to be thrown");

        verify(billRepository, times(1)).findBillById(billId);

    }

    @Test
    void approvePayment_BillDoesNotExist_ThrowsPaymentApprovalException() {
        // Arrange
        Integer billId = 456;

        when(billRepository.findBillById(billId)).thenReturn(null);

        // Act & Assert
        assertThrows(PaymentApprovalException.class, () -> {
            paymentService.approvePayment(billId);
        }, "Failed to approve payment. Bill with ID " + billId + " does not exist.");

        verify(billRepository, times(1)).findBillById(billId);
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
    void addCoupon_ValidRequest_ReturnsCreatedCoupon() {
        // Arrange
        CouponRequest request = new CouponRequest();
        request.setDiscount(10);

        Coupon coupon = new Coupon();
        coupon.setDiscount(10);

        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        // Act
        Coupon result = paymentService.addCoupon(request);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCode());
        assertEquals(10, result.getDiscount());
        verify(couponRepository, times(1)).save(any(Coupon.class));
    }
    @Test
    void getCouponByCode_ValidCode_ReturnsCoupon() {
        // Arrange
        String couponCode = "COUPON123";
        Coupon expectedCoupon = new Coupon(1, couponCode, 10, false);

        when(couponRepository.findByCode(couponCode)).thenReturn(expectedCoupon);

        // Act
        Coupon actualCoupon = paymentService.getCouponByCode(couponCode);

        // Assert
        assertNotNull(actualCoupon);
        assertEquals(expectedCoupon, actualCoupon);
        verify(couponRepository, times(1)).findByCode(couponCode);
    }

    @Test
    void getCouponByCode_InvalidCode_ThrowsCouponDoesNotExistException() {
        // Arrange
        String couponCode = "INVALID";

        when(couponRepository.findByCode(couponCode)).thenReturn(null);

        // Act & Assert
        assertThrows(CouponDoesNotExistException.class, () -> {
            paymentService.getCouponByCode(couponCode);
        });

        verify(couponRepository, times(1)).findByCode(couponCode);
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
    void addVoucher_ValidRequest_ReturnsCreatedVoucher() {
        // Arrange
        VoucherRequest request = new VoucherRequest();
        request.setAmount(50.0);

        Voucher voucher = new Voucher();
        voucher.setAmount(50.0);

        when(voucherRepository.save(any(Voucher.class))).thenReturn(voucher);

        // Act
        Voucher result = paymentService.addVoucher(request);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCode());
        assertEquals(50.0, result.getAmount(), 0.01);
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }


    @Test
    void getVoucherByCode_ValidCode_ReturnsVoucher() {
        // Arrange
        String voucherCode = "VOUCHER123";
        Voucher expectedVoucher = new Voucher(1, voucherCode, 150000, false);

        when(voucherRepository.findByCode(voucherCode)).thenReturn(expectedVoucher);

        // Act
        Voucher actualVoucher = paymentService.getVoucherByCode(voucherCode);

        // Assert
        assertNotNull(actualVoucher);
        assertEquals(expectedVoucher, actualVoucher);
        verify(voucherRepository, times(1)).findByCode(voucherCode);
    }

    @Test
    void getVoucherByCode_InvalidCode_ThrowsVoucherDoesNotExistException() {
        // Arrange
        String voucherCode = "INVALID";

        when(voucherRepository.findByCode(voucherCode)).thenReturn(null);

        // Act & Assert
        assertThrows(VoucherDoesNotExistException.class, () -> {
            paymentService.getVoucherByCode(voucherCode);
        });

        verify(voucherRepository, times(1)).findByCode(voucherCode);
    }

    @Test
    void addBillToCustomer_WithEmptyPaymentList_AddsBillAndReturnsTrue() {
        // Arrange
        Customer customer = new Customer();
        Bill bill = new Bill();

        // Act
        boolean result = paymentService.addBillToCustomer(customer, bill);

        // Assert
        assertTrue(result);
        assertEquals(1, customer.getPaymentList().size());
        assertEquals(bill, customer.getPaymentList().get(0));
    }

    @Test
    void addBillToCustomer_WithExistingPaymentList_AddsBillAndReturnsTrue() {
        // Arrange
        Customer customer = new Customer();
        Bill bill1 = new Bill();
        Bill bill2 = new Bill();
        List<Bill> paymentList = new ArrayList<>();
        paymentList.add(bill1);
        customer.setPaymentList(paymentList);

        // Act
        boolean result = paymentService.addBillToCustomer(customer, bill2);

        // Assert
        assertTrue(result);
        assertEquals(2, customer.getPaymentList().size());
        assertEquals(bill1, customer.getPaymentList().get(0));
        assertEquals(bill2, customer.getPaymentList().get(1));
    }
}