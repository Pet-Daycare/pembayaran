package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponvoucher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoucherRequestTest {

    @Test
     void testVoucherRequest() {
        double amount = 120000;

        VoucherRequest voucherRequest = new VoucherRequest(amount);

        assertEquals(amount, voucherRequest.getAmount(), 0.01);
    }

     @Test
     void testToString() {
         VoucherRequest voucherRequest = VoucherRequest.builder()
                 .amount(50000.0)
                 .build();

         String expectedString = "VoucherRequest(amount=50000.0)";
         String actualString = voucherRequest.toString();

         assertEquals(expectedString, actualString);
     }

    @Test
    void testCanEqual() {
        VoucherRequest voucherRequest1 = VoucherRequest.builder()
                .amount(50000.0)
                .build();
        VoucherRequest voucherRequest2 = VoucherRequest.builder()
                .amount(50000.0)
                .build();
        VoucherRequest voucherRequest3 = VoucherRequest.builder()
                .amount(100000.0)
                .build();

        assertTrue(voucherRequest1.canEqual(voucherRequest2));
        assertTrue(voucherRequest1.canEqual(voucherRequest3));
    }

    @Test
    void testEqualsSameObject() {
        VoucherRequest sut = new VoucherRequest();
        assertEquals(sut, sut);
    }

    @Test
    void testEqualsNull() {
        VoucherRequest sut = new VoucherRequest();
        assertNotEquals(null, sut);
    }

    @Test
    void testEqualsDifferentClass() {
        VoucherRequest sut = new VoucherRequest();
        assertNotEquals(sut, new Object());
    }

    @Test
    void testEqualsDifferentAmount() {
        VoucherRequest sut1 = new VoucherRequest(10000.0);
        VoucherRequest sut2 = new VoucherRequest(20000.0);
        assertNotEquals(sut1, sut2);
    }

    @Test
    void testEqualsSameAmount() {
        VoucherRequest sut1 = new VoucherRequest(10000.0);
        VoucherRequest sut2 = new VoucherRequest(10000.0);
        assertEquals(sut1, sut2);
    }
    @Test
    void testHashCode() {
        VoucherRequest voucherRequest1 = VoucherRequest.builder()
                .amount(50000.0)
                .build();
        VoucherRequest voucherRequest2 = VoucherRequest.builder()
                .amount(50000.0)
                .build();
        VoucherRequest voucherRequest3 = VoucherRequest.builder()
                .amount(100000.0)
                .build();

        assertEquals(voucherRequest1.hashCode(), voucherRequest2.hashCode());
        assertNotEquals(voucherRequest1.hashCode(), voucherRequest3.hashCode());
    }

    @Test
     void testBuilder() {
        VoucherRequest voucherRequest = VoucherRequest.builder()
                .amount(100000.0)
                .build();

        assertEquals(100000.0, voucherRequest.getAmount());
    }

     @Test
     void testBuilderToString() {
         VoucherRequest.VoucherRequestBuilder builder = VoucherRequest.builder()
                 .amount(50000.0);

         String expectedString = "VoucherRequest.VoucherRequestBuilder(amount=50000.0)";
         String actualString = builder.toString();

         assertEquals(expectedString, actualString);
     }
}
