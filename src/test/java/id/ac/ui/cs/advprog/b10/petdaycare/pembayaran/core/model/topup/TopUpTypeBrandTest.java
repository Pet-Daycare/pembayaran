package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.model.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpTypeBrand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopUpTypeBrandTest {
    @Test
    public void testToString() {
        assertEquals("BCA Transfer", TopUpTypeBrand.BCA_BANK_TRANSFER.toString());
        assertEquals("Dana", TopUpTypeBrand.DANA.toString());
        assertEquals("Gopay", TopUpTypeBrand.GOPAY.toString());
        assertEquals("Mandiri Transfer", TopUpTypeBrand.MANDIRI_BANK_TRANSFER.toString());
        assertEquals("Qris", TopUpTypeBrand.QRIS.toString());
    }

    @Test
    public void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            TopUpTypeBrand.INVALID.toString();
        });
    }
}
