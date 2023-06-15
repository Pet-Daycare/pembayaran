package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.adminfee;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminFeeTest {
    @Test
    public void testGetFee() {
        double expectedFee = 10.0;
        AdminFee adminFee = new AdminFeeImpl(expectedFee);

        double result = adminFee.getFee();

        assertEquals(expectedFee, result);
    }

    @Test
    public void testGetName() {
        AdminFee adminFee = new AdminFeeImpl(10.0);

        String result = adminFee.getName();

        assertEquals("AdminFeeImpl", result);
    }

    private static class AdminFeeImpl implements AdminFee {
        private final double fee;

        public AdminFeeImpl(double fee) {
            this.fee = fee;
        }

        @Override
        public Double getFee() {
            return fee;
        }
    }
}
