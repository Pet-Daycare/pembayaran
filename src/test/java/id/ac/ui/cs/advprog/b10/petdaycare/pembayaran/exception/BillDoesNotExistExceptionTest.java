package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class BillDoesNotExistExceptionTest {
    @Test
    void billDoesNotExistException_Message() {
        // Arrange
        Integer billId = 123;
        String expectedMessage = "Bill with id " + billId + " does not exist";

        // Act
        BillDoesNotExistException exception = new BillDoesNotExistException(billId);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
