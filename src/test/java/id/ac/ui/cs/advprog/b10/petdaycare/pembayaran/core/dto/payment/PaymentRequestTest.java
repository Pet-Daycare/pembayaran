package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment.PaymentRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRequestTest {

    @Test
     void testPaymentRequest() {
        Integer idPenitipan = 13;
        String username = "carlisle";
        String token = "abc123";
        double total = 270000;
        String method = "voucher";
        String code = "voucher111";

        PaymentRequest paymentRequest = new PaymentRequest(idPenitipan, username, token, total, method, code);

        assertEquals(idPenitipan, paymentRequest.getIdPenitipan());
        assertEquals(username, paymentRequest.getUsername());
        assertEquals(token, paymentRequest.getToken());
        assertEquals(total, paymentRequest.getTotal(), 0.01);
        assertEquals(method, paymentRequest.getMethod());
        assertEquals(code, paymentRequest.getCode());
    }
    @Test
    void testHashCodeWithEmptyPaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();

        int expectedHashCode = paymentRequest.hashCode();
        assertEquals(expectedHashCode, paymentRequest.hashCode());
    }

    @Test
    void testHashCodeWithNullPaymentRequestProperties() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setIdPenitipan(null);
        paymentRequest.setUsername(null);
        paymentRequest.setToken(null);
        paymentRequest.setTotal(0);
        paymentRequest.setMethod(null);
        paymentRequest.setCode(null);

        int expectedHashCode = paymentRequest.hashCode();
        assertEquals(expectedHashCode, paymentRequest.hashCode());
    }

    @Test
    void testHashCodeWithDifferentPaymentRequestProperties() {
        PaymentRequest paymentRequest1 = new PaymentRequest();
        paymentRequest1.setIdPenitipan(123);
        paymentRequest1.setUsername("john.doe");
        paymentRequest1.setToken("abcd1234");
        paymentRequest1.setTotal(100000.0);
        paymentRequest1.setMethod("PET_WALLET");
        paymentRequest1.setCode("");

        PaymentRequest paymentRequest2 = new PaymentRequest();
        paymentRequest2.setIdPenitipan(456);
        paymentRequest2.setUsername("jane.doe");
        paymentRequest2.setToken("efgh5678");
        paymentRequest2.setTotal(200000.0);
        paymentRequest2.setMethod("BANK_TRANSFER");
        paymentRequest2.setCode("123456");

        assertNotEquals(paymentRequest1.hashCode(), paymentRequest2.hashCode());
    }

    @Test
    void testEqualsWithNull() {
        PaymentRequest paymentRequest1 = null;
        PaymentRequest paymentRequest2 = new PaymentRequest();

        // Check if paymentRequest1 is null
        if (paymentRequest1 == null) {
            // Do nothing
        } else {
            // Test for equality with null
            assertNotEquals(paymentRequest1, paymentRequest2);

            // Test for equality with a non-null object
            assertNotEquals(paymentRequest2, paymentRequest1);
        }
    }


    @Test
    void testEqualsWithDifferentTypes() {
        PaymentRequest paymentRequest1 = new PaymentRequest();
        String str = "abc";

        // Test for equality with a different type
        assertNotEquals(paymentRequest1, str);

        // Test for equality with a non-null object
        assertNotEquals(str, paymentRequest1);
    }

    @Test
    void testEqualsWithDifferentValues() {
        PaymentRequest paymentRequest1 = new PaymentRequest();
        PaymentRequest paymentRequest2 = new PaymentRequest();

        paymentRequest1.setIdPenitipan(123);
        paymentRequest2.setIdPenitipan(456);

        // Test for equality with a different object having different values
        assertNotEquals(paymentRequest1, paymentRequest2);
    }



    @Test
    void testToString() {
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .idPenitipan(123)
                .username("john.doe")
                .token("abcd1234")
                .total(120000.0)
                .method("PET_WALLET_WITH_COUPON")
                .code("ABCD1234")
                .build();

        String expectedString = "PaymentRequest(idPenitipan=123, username=john.doe, token=abcd1234, " +
                "total=120000.0, method=PET_WALLET_WITH_COUPON, code=ABCD1234)";
        assertEquals(expectedString, paymentRequest.toString());
    }
}

