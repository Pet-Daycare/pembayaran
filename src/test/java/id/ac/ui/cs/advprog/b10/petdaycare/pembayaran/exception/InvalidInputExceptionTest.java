package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidInputExceptionTest {
    @Test
    void invalidInputException_Message() {
        // Arrange
        String expectedMessage = "Invalid input: Input is null";

        // Act
        InvalidInputException exception = new InvalidInputException();

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

}
