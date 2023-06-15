package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.QRFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.QRPromo;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.QrisFactory;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QrisFactoryTest {
    @Test
    public void testAddFee() {
        AdminFee expectedAdminFee = new QRFee();
        TopUpFactory factory = new QrisFactory();

        AdminFee result = factory.addFee();

        assertEquals(expectedAdminFee.getClass(), result.getClass());
    }

    @Test
    public void testAddCashback() {
        Cashback expectedCashback = new QRPromo();
        TopUpFactory factory = new QrisFactory();

        Cashback result = factory.addCashback();

        assertEquals(expectedCashback.getClass(), result.getClass());
    }
}
