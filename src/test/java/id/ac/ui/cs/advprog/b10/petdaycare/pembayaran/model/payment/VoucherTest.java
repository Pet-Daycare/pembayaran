package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class VoucherTest {

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
     void testRedeem_ValidAmount_BillPaidWithVoucher() {
        Bill redeemedBill = voucher.redeem(bill, customerService);

        assertTrue(redeemedBill.isPaid());
        assertEquals(PaymentMethod.PET_WALLET_WITH_VOUCHER, redeemedBill.getMethod());
        assertEquals(200.0, redeemedBill.getCustomerBalance());
        assertTrue(voucher.isRedeemed());
    }

    @Test
     void testRedeem_InvalidAmount_BillNotChanged() {
        bill.setTotal(100.0);

        Bill unchangedBill = voucher.redeem(bill, customerService);

        assertFalse(unchangedBill.isPaid());
        assertNull(unchangedBill.getMethod());
        assertEquals(0.0, unchangedBill.getCustomerBalance());
        assertFalse(voucher.isRedeemed());
    }

    @Test
     void testGetId() {
        Voucher voucher = new Voucher();
        voucher.setId(1);
        assertEquals(1, voucher.getId());
    }

    @Test
     void testToString() {
        Voucher voucher = new Voucher();
        voucher.setId(1);
        voucher.setCode("ABC123");
        voucher.setAmount(100000);
        voucher.setRedeemed(false);
        assertEquals("Voucher(id=1, code=ABC123, amount=100000.0, isRedeemed=false)", voucher.toString());
    }

    @Test
     void testCanEqual() {
        Voucher voucher = new Voucher();
        assertTrue(voucher.canEqual(voucher));
        assertFalse(voucher.canEqual(new Object()));
    }

    @Test
     void testHashCode() {
        Voucher voucher1 = new Voucher();
        voucher1.setId(1);
        voucher1.setCode("ABC123");
        voucher1.setAmount(10.00);
        voucher1.setRedeemed(false);

        Voucher voucher2 = new Voucher();
        voucher2.setId(1);
        voucher2.setCode("ABC123");
        voucher2.setAmount(10.00);
        voucher2.setRedeemed(false);

        assertEquals(voucher1.hashCode(), voucher2.hashCode());
    }

    @Test
     void testEquals() {
        Voucher voucher1 = new Voucher();
        voucher1.setId(1);
        voucher1.setCode("ABC123");
        voucher1.setAmount(10.00);
        voucher1.setRedeemed(false);

        Voucher voucher2 = new Voucher();
        voucher2.setId(1);
        voucher2.setCode("ABC123");
        voucher2.setAmount(10.00);
        voucher2.setRedeemed(false);

        Voucher voucher3 = new Voucher();
        voucher3.setId(2);
        voucher3.setCode("DEF456");
        voucher3.setAmount(20.00);
        voucher3.setRedeemed(true);

        Object object = new Object();

        assertTrue(voucher1.equals(voucher2));
        assertFalse(voucher1.equals(voucher3));
        assertFalse(voucher1.equals(object));
    }

    @Test
    void testBuilderToString() {
       Voucher.VoucherBuilder builder = Voucher.builder()
               .id(1)
               .code("ABC123")
               .amount(10.00)
               .isRedeemed(false);

       String expectedString = "Voucher.VoucherBuilder(id=1, code=ABC123, amount=10.0, isRedeemed=false)";
       String actualString = builder.toString();

       assertEquals(expectedString, actualString);
    }


 }
