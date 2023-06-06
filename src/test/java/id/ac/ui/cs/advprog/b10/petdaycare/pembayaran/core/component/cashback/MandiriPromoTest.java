package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.cashback;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.MandiriPromo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MandiriPromoTest {
    @Test
    public void testGetCashback() {
        double expectedCashback = 0.05;
        MandiriPromo mandiriPromo = new MandiriPromo();

        double result = mandiriPromo.getCashback();

        assertEquals(expectedCashback, result);
    }
}
