package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.cashback;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashbackTest {
    @Test
    public void testGetCashback() {
        double expectedCashback = 0.0;
        Cashback cashback = new CashbackImpl();

        double result = cashback.getCashback();

        assertEquals(expectedCashback, result);
    }

    @Test
    public void testGetName() {
        Cashback cashback = new CashbackImpl();

        String result = cashback.getName();

        assertEquals("CashbackImpl", result);
    }

    private static class CashbackImpl implements Cashback {
        @Override
        public double getCashback() {
            return 0.0;
        }
    }
}
