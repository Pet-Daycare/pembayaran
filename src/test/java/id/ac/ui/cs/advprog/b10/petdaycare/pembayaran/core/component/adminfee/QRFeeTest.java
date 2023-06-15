package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.component.adminfee;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.QRFee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QRFeeTest {

    @Test
    public void testGetFee() {
        // Arrange
        QRFee qrFee = new QRFee();

        // Act
        Double fee = qrFee.getFee();

        // Assert
        assertEquals(0.7/100, fee);
    }
}
