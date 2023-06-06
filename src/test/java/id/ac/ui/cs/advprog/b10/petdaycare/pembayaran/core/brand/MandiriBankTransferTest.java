package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 class MandiriBankTransferTest {
    private MandiriBankTransfer mandiriBankTransfer;
    private TopUpFactory topUpFactory;

    @BeforeEach
    public void setUp() {
        topUpFactory = mock(TopUpFactory.class);
        mandiriBankTransfer = new MandiriBankTransfer(topUpFactory);
    }

    @Test
     void testCreate() {
        AdminFee expectedAdminFee = new AdminFee() {
            @Override
            public Double getFee() {
                return null;
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
        mandiriBankTransfer.create();

        // Verify the values
        assertEquals(expectedAdminFee, mandiriBankTransfer.getAdminFee());
        assertEquals(expectedCashback, mandiriBankTransfer.getCashback());
    }

    @Test
     void testCalculateSummaryAmount() {
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
        mandiriBankTransfer.setAdminFee(adminFee);
        mandiriBankTransfer.setCashback(cashback);

        double expectedSummaryTopUp = cashback.getCashback() - adminFee.getFee();

        // Perform the calculateSummaryAmount method
        mandiriBankTransfer.calculateSummaryAmount();

        // Verify the value
        assertEquals(expectedSummaryTopUp, mandiriBankTransfer.getSummaryTopUp());
    }
}
