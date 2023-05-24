package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoucherDoesNotExistExceptionTest {
    @Test
    void voucherDoesNotExistException_Message() {
        // Arrange
        String voucherCode = "DEF456";
        String expectedMessage = "Voucher with code " + voucherCode + " does not exist";

        // Act
        VoucherDoesNotExistException exception = new VoucherDoesNotExistException(voucherCode);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
