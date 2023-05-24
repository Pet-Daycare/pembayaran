package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VoucherTest {

    @Mock
    private CustomerService customerService;

    private Voucher voucher;
    private Bill bill;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        voucher = Voucher.builder()
                .id(1)
                .code("XYZ789")
                .amount(50.0)
                .isRedeemed(false)
                .build();

        bill = new Bill();
        bill.setUsername("john");
        bill.setTotal(50.0);

        customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(200.0);

        when(customerService.findCustomer("john")).thenReturn(customer);
    }

    @Test
    public void testRedeem_ValidAmount_BillPaidWithVoucher() {
        Bill redeemedBill = voucher.redeem(bill, customerService);

        assertTrue(redeemedBill.isPaid());
        assertEquals(PaymentMethod.PET_WALLET_WITH_VOUCHER, redeemedBill.getMethod());
        assertEquals(200.0, redeemedBill.getCustomerBalance());
        assertTrue(voucher.isRedeemed());
    }

    @Test
    public void testRedeem_InvalidAmount_BillNotChanged() {
        bill.setTotal(100.0);

        Bill unchangedBill = voucher.redeem(bill, customerService);

        assertFalse(unchangedBill.isPaid());
        assertNull(unchangedBill.getMethod());
        assertEquals(0.0, unchangedBill.getCustomerBalance());
        assertFalse(voucher.isRedeemed());
    }
}
