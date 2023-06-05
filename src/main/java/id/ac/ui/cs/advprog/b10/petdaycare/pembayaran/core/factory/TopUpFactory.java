package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;

public interface TopUpFactory {
    public AdminFee addFee();
    public Cashback addCashback();
}
