package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.TansferBankFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.BCAPromo;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;

public class BCABankTransferFactory implements TopUpFactory {
    @Override
    public AdminFee addFee(){
        return new TansferBankFee();
    }
    @Override
    public Cashback addCashback(){
        return new BCAPromo();
    }

}
