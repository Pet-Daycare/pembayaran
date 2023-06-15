package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.QRFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.DanaPromo;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.DanaFactory;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DanaFactoryTest {
    @Test
    public void testAddFee() {
        AdminFee expectedAdminFee = new QRFee();
        TopUpFactory factory = new DanaFactory();

        AdminFee result = factory.addFee();

        assertEquals(expectedAdminFee.getClass(), result.getClass());
    }

    @Test
    public void testAddCashback() {
        Cashback expectedCashback = new DanaPromo();
        TopUpFactory factory = new DanaFactory();

        Cashback result = factory.addCashback();

        assertEquals(expectedCashback.getClass(), result.getClass());
    }
}
