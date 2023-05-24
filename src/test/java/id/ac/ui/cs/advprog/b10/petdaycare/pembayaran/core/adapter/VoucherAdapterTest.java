package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter.VoucherAdapter;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.Payment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VoucherAdapterTest {

    @Mock
    private Voucher voucher;

    @Mock
    private CustomerService customerService;

    private VoucherAdapter voucherAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        voucherAdapter = new VoucherAdapter(voucher, customerService);
    }

    @Test
    public void testPay_WithRedeemedVoucher_ShouldReturnSameBill() {
        // Arrange
        Bill bill = new Bill();
        when(voucher.isRedeemed()).thenReturn(true);

        // Act
        Bill result = voucherAdapter.pay(bill);

        // Assert
        assertSame(bill, result);
        verify(voucher, times(1)).isRedeemed();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testPay_WithUnredeemedVoucher_ShouldRedeemVoucherAndReturnBill() {
        // Arrange
        Bill bill = new Bill();
        Bill redeemedBill = new Bill();
        when(voucher.isRedeemed()).thenReturn(false);
        when(voucher.redeem(bill, customerService)).thenReturn(redeemedBill);

        // Act
        Bill result = voucherAdapter.pay(bill);

        // Assert
        assertSame(redeemedBill, result);
        verify(voucher, times(1)).isRedeemed();
        verify(voucher, times(1)).redeem(bill, customerService);
    }

    // Additional test cases can be added here...

}
