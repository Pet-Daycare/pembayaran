package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void setId_GetId_ReturnsSetValue() {
        // Arrange
        int expectedId = 1;
        Customer customer = new Customer();

        // Act
        customer.setId(expectedId);
        int actualId = customer.getId();

        // Assert
        assertEquals(expectedId, actualId);
    }
}

