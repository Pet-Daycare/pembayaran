package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TopUpTest {

    @Test
    public void testGetterAndSetterMethods() {
        TopUp topUp = new TopUp();

        topUp.setId("123456");
        assertEquals("123456", topUp.getId());

        topUp.setUsername("john");
        assertEquals("john", topUp.getUsername());

        topUp.setCustomerId(1);
        assertEquals(1, topUp.getCustomerId());

        topUp.setTypeMethod("GOPAY");
        assertEquals(TopUpTypeBrand.GOPAY, TopUpTypeBrand.valueOf(topUp.getTypeMethod()));

        topUp.setTimeTaken("10:30 AM");
        assertEquals("10:30 AM", topUp.getTimeTaken());

        topUp.setNominal(100.0);
        assertEquals(100.0, topUp.getNominal());

        topUp.setValidate(true);
        assertTrue(topUp.isValidate());
    }
}
