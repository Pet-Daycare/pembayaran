package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.cashback;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.BCAPromo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BCAPromoTest {
    @Test
    public void testGetCashback() {
        double expectedCashback = 0.1;
        BCAPromo bcaPromo = new BCAPromo();

        double result = bcaPromo.getCashback();

        assertEquals(expectedCashback, result);
    }
}
