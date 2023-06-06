package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.BCABankTransfer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BCABankTransferTest {
    private BCABankTransfer bcaBankTransfer;
    private TopUpFactory topUpFactory;

    @BeforeEach
    public void setUp() {
        topUpFactory = mock(TopUpFactory.class);
        bcaBankTransfer = new BCABankTransfer(topUpFactory);
    }

    @Test
    public void testCreate() {
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
        bcaBankTransfer.create();

        // Verify the values
        assertEquals(expectedAdminFee, bcaBankTransfer.getAdminFee());
        assertEquals(expectedCashback, bcaBankTransfer.getCashback());
    }

    @Test
    public void testCalculateSummaryAmount() {
        double expectedSummaryTopUp = 0.0;

        // Perform the calculateSummaryAmount method
        bcaBankTransfer.calculateSummaryAmount();

        // Verify the value
        assertEquals(expectedSummaryTopUp, bcaBankTransfer.getSummaryTopUp());
    }
}
