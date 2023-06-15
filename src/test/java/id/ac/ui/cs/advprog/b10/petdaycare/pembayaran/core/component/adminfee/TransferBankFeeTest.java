package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.adminfee;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.TransferBankFee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransferBankFeeTest {

    @Test
    public void testGetFee() {
        // Arrange
        TransferBankFee transferBankFee = new TransferBankFee();

        // Act
        Double fee = transferBankFee.getFee();

        // Assert
        assertEquals(1.0/100, fee);
    }
}
