package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.cashback;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.QRPromo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QRPromoTest {
    @Test
    public void testGetCashback() {
        double expectedCashback = 0.0;
        QRPromo qrPromo = new QRPromo();

        double result = qrPromo.getCashback();

        assertEquals(expectedCashback, result);
    }
}
