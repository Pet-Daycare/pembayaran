package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDoesNotExistExceptionTest {
    @Test
    void customerDoesNotExistException_Message() {
        // Arrange
        String username = "jane456";
        String expectedMessage = "Customer with id " + username + " does not exist";

        // Act
        CustomerDoesNotExistException exception = new CustomerDoesNotExistException(username);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

}
