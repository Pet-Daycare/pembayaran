import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.CouponAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.Payment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CouponAdapterTest {

    @Mock
    private Coupon coupon;

    @Mock
    private CustomerService customerService;

    private CouponAdapter couponAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        couponAdapter = new CouponAdapter(coupon, customerService);
    }

    @Test
    public void testPay_WithRedeemedCoupon_ShouldReturnSameBill() throws InterruptedException {
        // Arrange
        Bill bill = new Bill();
        when(coupon.isRedeemed()).thenReturn(true);

        // Act
        Bill result = couponAdapter.pay(bill);

        // Assert
        assertSame(bill, result);
        verify(coupon, times(1)).isRedeemed();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testPay_WithUnredeemedCoupon_ShouldRedeemCouponAndReturnBill() throws InterruptedException {
        // Arrange
        Bill bill = new Bill();
        Bill redeemedBill = new Bill();
        when(coupon.isRedeemed()).thenReturn(false);
        when(coupon.redeem(bill, customerService)).thenReturn(redeemedBill);

        // Act
        Bill result = couponAdapter.pay(bill);

        // Assert
        assertSame(redeemedBill, result);
        verify(coupon, times(1)).isRedeemed();
        verify(coupon, times(1)).redeem(bill, customerService);
    }

    // Additional test cases can be added here...

}
