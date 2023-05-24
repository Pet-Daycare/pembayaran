package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminFee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminFee.QRFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.DanaPromo;

public class DanaFactory implements TopUpFactory{

    @Override
    public AdminFee addFee(){
        return new QRFee();
    }
    @Override
    public Cashback addCashback(){return new DanaPromo();
    }

}
