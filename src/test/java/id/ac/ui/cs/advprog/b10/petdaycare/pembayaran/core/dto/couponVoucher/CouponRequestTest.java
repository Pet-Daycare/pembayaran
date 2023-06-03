package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponVoucher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouponRequestTest {

    @Test
     void testCouponRequest() {
        double discount = 10;

        CouponRequest couponRequest = new CouponRequest(discount);

        assertEquals(discount, couponRequest.getDiscount(), 0.01);
    }

     @Test
     void testCanEqual() {
         CouponRequest couponRequest1 = CouponRequest.builder()
                 .discount(0.15)
                 .build();
         CouponRequest couponRequest2 = CouponRequest.builder()
                 .discount(0.15)
                 .build();
         CouponRequest couponRequest3 = CouponRequest.builder()
                 .discount(0.20)
                 .build();

         assertTrue(couponRequest1.canEqual(couponRequest2));
         assertTrue(couponRequest1.canEqual(couponRequest3));
     }

     @Test
     void testToString() {
         double discount = 15;

         CouponRequest couponRequest = CouponRequest.builder()
                 .discount(discount)
                 .build();

         String expectedString = "CouponRequest(discount=" + discount + ")";
         assertEquals(expectedString, couponRequest.toString());
     }

    @Test
    void testHashCode() {
        double discount1 = 15;
        double discount2 = 15;
        double discount3 = 10;

        CouponRequest couponRequest1 = CouponRequest.builder()
                .discount(discount1)
                .build();

        CouponRequest couponRequest2 = CouponRequest.builder()
                .discount(discount2)
                .build();

        CouponRequest couponRequest3 = CouponRequest.builder()
                .discount(discount3)
                .build();

        assertEquals(couponRequest1.hashCode(), couponRequest2.hashCode());
        assertNotEquals(couponRequest1.hashCode(), couponRequest3.hashCode());
    }
     @Test
     void testEquals() {
         double discount = 0.15;

         CouponRequest couponRequest1 = CouponRequest.builder()
                 .discount(discount)
                 .build();
         CouponRequest couponRequest2 = CouponRequest.builder()
                 .discount(discount)
                 .build();
         CouponRequest couponRequest3 = CouponRequest.builder()
                 .discount(0.20)
                 .build();

         // Test for equality with itself
         assertTrue(couponRequest1.equals(couponRequest1));

         // Test for equality with a different object type
         assertFalse(couponRequest1.equals("0.15"));

         // Test for equality with a different object having the same values
         assertTrue(couponRequest1.equals(couponRequest2));

         // Test for equality with a different object having different values
         assertFalse(couponRequest1.equals(couponRequest3));
     }

    @Test
    void testBuilder() {
        double discount = 15;

        CouponRequest couponRequest = CouponRequest.builder()
                .discount(discount)
                .build();

        assertEquals(discount, couponRequest.getDiscount());
    }

    @Test
    void testBuilderToString() {
        CouponRequest.CouponRequestBuilder builder = CouponRequest.builder()
                .discount(10);

        String expectedString = "CouponRequest.CouponRequestBuilder(discount=10.0)";
        String actualString = builder.toString();

        assertEquals(expectedString, actualString);
    }
}

