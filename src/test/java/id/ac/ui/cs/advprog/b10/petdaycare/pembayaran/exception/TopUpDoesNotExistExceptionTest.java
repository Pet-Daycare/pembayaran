package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopUpDoesNotExistExceptionTest {
    @Test
    void topUpDoesNotExistException_Message() {
        // Arrange
        Integer topUpId = 789;
        String expectedMessage = "Top Up detail with ID " + topUpId + " does not exist";

        // Act
        TopUpDoesNotExistException exception = new TopUpDoesNotExistException(topUpId);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

}
