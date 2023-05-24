package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CouponDoesNotExistExceptionTest {
    @Test
    void couponDoesNotExistException_Message() {
        // Arrange
        String couponCode = "ABC123";
        String expectedMessage = "Coupon with code " + couponCode + " does not exist";

        // Act
        CouponDoesNotExistException exception = new CouponDoesNotExistException(couponCode);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
