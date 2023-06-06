package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.cashback;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.DanaPromo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DanaPromoTest {
    @Test
    public void testGetCashback() {
        double expectedCashback = 0.07;
        DanaPromo danaPromo = new DanaPromo();

        double result = danaPromo.getCashback();

        assertEquals(expectedCashback, result);
    }
}
