package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class CouponTest {

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
     void testRedeem_NotRedeemedWithEnoughBalance_BillPaidWithDiscount() throws InterruptedException {
        Bill redeemedBill = coupon.redeem(bill, customerService);

        assertTrue(redeemedBill.isPaid());
        assertEquals(PaymentMethod.PET_WALLET_WITH_COUPON, redeemedBill.getMethod());
        assertEquals(90.0, redeemedBill.getTotal());
        assertEquals(110.0, redeemedBill.getCustomerBalance());
        assertTrue(coupon.isRedeemed());
        assertEquals(110.0, customer.getBalance());
    }

    @Test
     void testRedeem_AlreadyRedeemed_BillNotChanged() throws InterruptedException {
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
     void testRedeem_NotRedeemedWithInsufficientBalance_BillNotChanged() throws InterruptedException {
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
     void testIsEnoughBalance_EnoughBalance_ReturnsTrue() {
        boolean enoughBalance = coupon.isEnoughBalance(200.0, 100.0);

        assertTrue(enoughBalance);
    }

    @Test
     void testIsEnoughBalance_InsufficientBalance_ReturnsFalse() {
        boolean enoughBalance = coupon.isEnoughBalance(50.0, 100.0);

        assertFalse(enoughBalance);
    }

    @Test
    void testToString() {
        Integer id = 1;
        String code = "ABCD1234";
        double discount = 15.0;
        boolean isRedeemed = false;

        Coupon coupon = Coupon.builder()
                .id(id)
                .code(code)
                .discount(discount)
                .isRedeemed(isRedeemed)
                .build();

        String expectedString = "Coupon(id=1, code=ABCD1234, discount=15.0, isRedeemed=false)";
        String actualString = coupon.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void testSetId() {
        Integer originalId = 1;
        Integer newId = 2;

        Coupon coupon = Coupon.builder()
                .id(originalId)
                .code("ABCD1234")
                .discount(15.0)
                .isRedeemed(false)
                .build();

        coupon.setId(newId);

        Integer updatedId = coupon.getId();
        assertEquals(newId, updatedId);
    }

    @Test
    void testGetId() {
        Integer expectedId = 1;

        Coupon coupon = Coupon.builder()
                .id(expectedId)
                .code("ABCD1234")
                .discount(15.0)
                .isRedeemed(false)
                .build();

        Integer actualId = coupon.getId();
        assertEquals(expectedId, actualId);
    }

     @Test
     void testCanEqual() {
         Coupon coupon1 = new Coupon();
         Coupon coupon2 = new Coupon();

         // Test for equality with itself
         assertTrue(coupon1.canEqual(coupon1));

         // Test for equality with a non-null object of the same type
         assertTrue(coupon1.canEqual(coupon2));

         // Test for equality with a null object
         assertFalse(coupon1.canEqual(null));
     }

     @Test
     void testHashCode() {
         Coupon coupon1 = Coupon.builder()
                 .id(1)
                 .code("ABCD1234")
                 .discount(15.0)
                 .isRedeemed(false)
                 .build();

         Coupon coupon2 = Coupon.builder()
                 .id(1)
                 .code("ABCD1234")
                 .discount(15.0)
                 .isRedeemed(false)
                 .build();

         Coupon coupon3 = Coupon.builder()
                 .id(2)
                 .code("EFGH5678")
                 .discount(20.0)
                 .isRedeemed(true)
                 .build();

         // Test for equality with itself
         assertEquals(coupon1.hashCode(), coupon1.hashCode());

         // Test for equality with a non-null object of the same type
         assertEquals(coupon1.hashCode(), coupon2.hashCode());

         // Test for inequality with a different object of the same type
         assertNotEquals(coupon1.hashCode(), coupon3.hashCode());

         // Test for inequality with a null object
         assertNotEquals(0, coupon1.hashCode());
     }

     @Test
     void testEquals() {
         Coupon coupon1 = Coupon.builder()
                 .id(1)
                 .code("ABCD1234")
                 .discount(15.0)
                 .isRedeemed(false)
                 .build();

         Coupon coupon2 = Coupon.builder()
                 .id(1)
                 .code("ABCD1234")
                 .discount(15.0)
                 .isRedeemed(false)
                 .build();

         Coupon coupon3 = Coupon.builder()
                 .id(2)
                 .code("EFGH5678")
                 .discount(20.0)
                 .isRedeemed(true)
                 .build();

         // Test for equality with itself
         assertEquals(coupon1, coupon1);

         // Test for equality with a non-null object of the same type
         assertEquals(coupon1, coupon2);

         // Test for inequality with a different object of the same type
         assertNotEquals(coupon1, coupon3);

         // Test for inequality with null
         assertNotEquals(null, coupon1);

         // Test for inequality with a different type of object
         assertNotEquals("ABCD1234", coupon1);
     }

    @Test
    void testBuilderToString() {
        Coupon.CouponBuilder builder = Coupon.builder()
                .id(1)
                .code("ABCD1234")
                .discount(15.0)
                .isRedeemed(false);

        String expectedString = "Coupon.CouponBuilder(id=1, code=ABCD1234, discount=15.0, isRedeemed=false)";

        assertEquals(expectedString, builder.toString());
    }
}
