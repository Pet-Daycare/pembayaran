package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.TransferBankFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.MandiriPromo;

public class MandiriBankTransferFactory implements TopUpFactory{
    @Override
    public AdminFee addFee(){
        return new TransferBankFee();
    }
    @Override
    public Cashback addCashback(){
        return new MandiriPromo();
    }

}
