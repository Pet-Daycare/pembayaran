package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerAlreadyExistExceptionTest {
    @Test
    void customerAlreadyExistException_Message() {
        // Arrange
        String username = "john123";
        String expectedMessage = "Customer with id " + username + " already exist!";

        // Act
        CustomerAlreadyExistException exception = new CustomerAlreadyExistException(username);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
