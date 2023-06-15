package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpTypeBrand;

public class SystemBuilderTest {

    @Test
    public void testCreateTopUp_Gopay() {
        // Arrange
        TopUpTypeBrand topUpTypeBrand = TopUpTypeBrand.GOPAY;
        SystemBuilder systemBuilder = new SystemBuilder();

        // Act
        TopUpCalulate topUp = systemBuilder.createTopUp(topUpTypeBrand);

        // Assert
        assertTrue(topUp instanceof Gopay);
    }

    @Test
    public void testCreateTopUp_Dana() {
        // Arrange
        TopUpTypeBrand topUpTypeBrand = TopUpTypeBrand.DANA;
        SystemBuilder systemBuilder = new SystemBuilder();

        // Act
        TopUpCalulate topUp = systemBuilder.createTopUp(topUpTypeBrand);

        // Assert
        assertTrue(topUp instanceof Dana);
    }

    @Test
    public void testCreateTopUp_Qris() {
        // Arrange
        TopUpTypeBrand topUpTypeBrand = TopUpTypeBrand.QRIS;
        SystemBuilder systemBuilder = new SystemBuilder();

        // Act
        TopUpCalulate topUp = systemBuilder.createTopUp(topUpTypeBrand);

        // Assert
        assertTrue(topUp instanceof Qris);
    }

    @Test
    public void testCreateTopUp_MandiriBankTransfer() {
        // Arrange
        TopUpTypeBrand topUpTypeBrand = TopUpTypeBrand.MANDIRI_BANK_TRANSFER;
        SystemBuilder systemBuilder = new SystemBuilder();

        // Act
        TopUpCalulate topUp = systemBuilder.createTopUp(topUpTypeBrand);

        // Assert
        assertTrue(topUp instanceof MandiriBankTransfer);
    }

    @Test
    public void testCreateTopUp_BCABankTransfer() {
        // Arrange
        TopUpTypeBrand topUpTypeBrand = TopUpTypeBrand.BCA_BANK_TRANSFER;
        SystemBuilder systemBuilder = new SystemBuilder();

        // Act
        TopUpCalulate topUp = systemBuilder.createTopUp(topUpTypeBrand);

        // Assert
        assertTrue(topUp instanceof BCABankTransfer);
    }
}
