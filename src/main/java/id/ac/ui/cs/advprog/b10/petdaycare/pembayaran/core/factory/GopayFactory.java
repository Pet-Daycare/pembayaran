package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.EWalletFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.GopayPromo;

public class GopayFactory implements TopUpFactory{
    @Override
    public AdminFee addFee(){
        return new EWalletFee();
    }
    @Override
    public Cashback addCashback(){
        return new GopayPromo();
    }

}
