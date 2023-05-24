package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CouponTest {

    @Mock
    private CustomerService customerService;

    private Coupon coupon;
    private Bill bill;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        coupon = Coupon.builder()
                .id(1)
                .code("ABC123")
                .discount(10.0)
                .isRedeemed(false)
                .build();

        bill = new Bill();
        bill.setUsername("john");
        bill.setTotal(100.0);

        customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(200.0);

        when(customerService.findCustomer("john")).thenReturn(customer);
    }

    @Test
    public void testRedeem_NotRedeemedWithEnoughBalance_BillPaidWithDiscount() throws InterruptedException {
        Bill redeemedBill = coupon.redeem(bill, customerService);

        assertTrue(redeemedBill.isPaid());
        assertEquals(PaymentMethod.PET_WALLET_WITH_COUPON, redeemedBill.getMethod());
        assertEquals(90.0, redeemedBill.getTotal());
        assertEquals(110.0, redeemedBill.getCustomerBalance());
        assertTrue(coupon.isRedeemed());
        assertEquals(110.0, customer.getBalance());
    }

    @Test
    public void testRedeem_AlreadyRedeemed_BillNotChanged() throws InterruptedException {
        coupon.setRedeemed(true);

        Bill unchangedBill = coupon.redeem(bill, customerService);

        assertFalse(unchangedBill.isPaid());
        assertNull(unchangedBill.getMethod());
        assertEquals(100.0, unchangedBill.getTotal());
        assertEquals(0.0, unchangedBill.getCustomerBalance());
        assertTrue(coupon.isRedeemed());
        assertEquals(200.0, customer.getBalance());
    }

    @Test
    public void testRedeem_NotRedeemedWithInsufficientBalance_BillNotChanged() throws InterruptedException {
        customer.setBalance(50.0);

        Bill unchangedBill = coupon.redeem(bill, customerService);

        assertFalse(unchangedBill.isPaid());
        assertNull(unchangedBill.getMethod());
        assertEquals(100.0, unchangedBill.getTotal());
        assertEquals(0.0, unchangedBill.getCustomerBalance());
        assertFalse(coupon.isRedeemed());
        assertEquals(50.0, customer.getBalance());
    }

    @Test
    public void testIsEnoughBalance_EnoughBalance_ReturnsTrue() {
        boolean enoughBalance = coupon.isEnoughBalance(200.0, 100.0);

        assertTrue(enoughBalance);
    }

    @Test
    public void testIsEnoughBalance_InsufficientBalance_ReturnsFalse() {
        boolean enoughBalance = coupon.isEnoughBalance(50.0, 100.0);

        assertFalse(enoughBalance);
    }
}
