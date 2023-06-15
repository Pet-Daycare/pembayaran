package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.TransferBankFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.MandiriPromo;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.MandiriBankTransferFactory;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MandiriBankTransferFactoryTest {
    @Test
    public void testAddFee() {
        AdminFee expectedAdminFee = new TransferBankFee();
        TopUpFactory factory = new MandiriBankTransferFactory();

        AdminFee result = factory.addFee();

        assertEquals(expectedAdminFee.getClass(), result.getClass());
    }

    @Test
    public void testAddCashback() {
        Cashback expectedCashback = new MandiriPromo();
        TopUpFactory factory = new MandiriBankTransferFactory();

        Cashback result = factory.addCashback();

        assertEquals(expectedCashback.getClass(), result.getClass());
    }
}
