package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.TransferBankFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.BCAPromo;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.BCABankTransferFactory;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BCABankTransferFactoryTest {
    @Test
    public void testAddFee() {
        AdminFee expectedAdminFee = new TransferBankFee();
        TopUpFactory factory = new BCABankTransferFactory();

        AdminFee result = factory.addFee();

        assertEquals(expectedAdminFee.getClass(), result.getClass());
    }

    @Test
    public void testAddCashback() {
        Cashback expectedCashback = new BCAPromo();
        TopUpFactory factory = new BCABankTransferFactory();

        Cashback result = factory.addCashback();

        assertEquals(expectedCashback.getClass(), result.getClass());
    }
}
