package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopUpRequestTest {
    @Test
    public void testGetterAndSetter() {
        String expectedUsername = "john";
        String expectedToken = "abc123";
        String expectedTypeMethod = "creditCard";
        double expectedNominal = 100.0;

        TopUpRequest topUpRequest = new TopUpRequest();
        topUpRequest.setUsername(expectedUsername);
        topUpRequest.setToken(expectedToken);
        topUpRequest.setTypeMethod(expectedTypeMethod);
        topUpRequest.setNominal(expectedNominal);

        assertEquals(expectedUsername, topUpRequest.getUsername());
        assertEquals(expectedToken, topUpRequest.getToken());
        assertEquals(expectedTypeMethod, topUpRequest.getTypeMethod());
        assertEquals(expectedNominal, topUpRequest.getNominal(), 0.01);
    }

    @Test
    public void testBuilder() {
        String expectedUsername = "john";
        String expectedToken = "abc123";
        String expectedTypeMethod = "creditCard";
        double expectedNominal = 100.0;

        TopUpRequest topUpRequest = TopUpRequest.builder()
                .username(expectedUsername)
                .token(expectedToken)
                .typeMethod(expectedTypeMethod)
                .nominal(expectedNominal)
                .build();

        assertEquals(expectedUsername, topUpRequest.getUsername());
        assertEquals(expectedToken, topUpRequest.getToken());
        assertEquals(expectedTypeMethod, topUpRequest.getTypeMethod());
        assertEquals(expectedNominal, topUpRequest.getNominal(), 0.01);
    }

    @Test
    public void testAllArgsConstructor() {
        String expectedUsername = "john";
        String expectedToken = "abc123";
        String expectedTypeMethod = "creditCard";
        double expectedNominal = 100.0;

        TopUpRequest topUpRequest = new TopUpRequest(expectedUsername, expectedToken, expectedTypeMethod, expectedNominal);

        assertEquals(expectedUsername, topUpRequest.getUsername());
        assertEquals(expectedToken, topUpRequest.getToken());
        assertEquals(expectedTypeMethod, topUpRequest.getTypeMethod());
        assertEquals(expectedNominal, topUpRequest.getNominal(), 0.01);
    }

    @Test
    public void testNoArgsConstructor() {
        TopUpRequest topUpRequest = new TopUpRequest();

        assertEquals(null, topUpRequest.getUsername());
        assertEquals(null, topUpRequest.getToken());
        assertEquals(null, topUpRequest.getTypeMethod());
        assertEquals(0.0, topUpRequest.getNominal(), 0.01);
    }
}
