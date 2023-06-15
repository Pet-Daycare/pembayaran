package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerRequestTest {
    @Test
    public void testGetterAndSetter() {
        String expectedUsername = "john";
        String expectedToken = "abc123";

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setUsername(expectedUsername);
        customerRequest.setToken(expectedToken);

        assertEquals(expectedUsername, customerRequest.getUsername());
        assertEquals(expectedToken, customerRequest.getToken());
    }

    @Test
    public void testBuilder() {
        String expectedUsername = "john";
        String expectedToken = "abc123";

        CustomerRequest customerRequest = CustomerRequest.builder()
                .username(expectedUsername)
                .token(expectedToken)
                .build();

        assertEquals(expectedUsername, customerRequest.getUsername());
        assertEquals(expectedToken, customerRequest.getToken());
    }

    @Test
    public void testAllArgsConstructor() {
        String expectedUsername = "john";
        String expectedToken = "abc123";

        CustomerRequest customerRequest = new CustomerRequest(expectedUsername, expectedToken);

        assertEquals(expectedUsername, customerRequest.getUsername());
        assertEquals(expectedToken, customerRequest.getToken());
    }

    @Test
    public void testNoArgsConstructor() {
        CustomerRequest customerRequest = new CustomerRequest();

        assertEquals(null, customerRequest.getUsername());
        assertEquals(null, customerRequest.getToken());
    }
}
