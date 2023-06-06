package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.Qris;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QrisTest {
    private Qris qris;
    private TopUpFactory topUpFactory;

    @BeforeEach
    public void setUp() {
        topUpFactory = mock(TopUpFactory.class);
        qris = new Qris(topUpFactory);
    }

    @Test
    public void testCreate() {
        AdminFee expectedAdminFee = new AdminFee() {
            @Override
            public Double getFee() {
                return 0.0;
            }
        };
        Cashback expectedCashback = new Cashback() {
            @Override
            public double getCashback() {
                return 0;
            }
        };

        // Mock the behavior of TopUpFactory
        when(topUpFactory.addFee()).thenReturn(expectedAdminFee);
        when(topUpFactory.addCashback()).thenReturn(expectedCashback);

        // Perform the create method
        qris.create();

        // Verify the values
        assertEquals(expectedAdminFee, qris.getAdminFee());
        assertEquals(expectedCashback, qris.getCashback());
    }

    @Test
    public void testCalculateSummaryAmount() {
        AdminFee adminFee = new AdminFee() {
            @Override
            public Double getFee() {
                return 0.0;
            }
        };
        Cashback cashback = new Cashback() {
            @Override
            public double getCashback() {
                return 0;
            }
        };
        qris.setAdminFee(adminFee);
        qris.setCashback(cashback);

        double expectedSummaryTopUp = cashback.getCashback() - adminFee.getFee();

        // Perform the calculateSummaryAmount method
        qris.calculateSummaryAmount();

        // Verify the value
        assertEquals(expectedSummaryTopUp, qris.getSummaryTopUp());
    }
}
