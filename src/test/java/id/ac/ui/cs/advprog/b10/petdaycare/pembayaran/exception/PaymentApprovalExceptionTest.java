package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentApprovalExceptionTest {
    @Test
    void paymentApprovalException_Message() {
        // Arrange
        Integer billId = 456;
        String expectedMessage = "Failed to approve payment. Bill with ID " + billId + " does not exist.";

        // Act
        PaymentApprovalException exception = new PaymentApprovalException(billId);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
