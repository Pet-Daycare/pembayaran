package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee;

public class EWalletFee implements AdminFee{

    @Override
    public Double getFee(){
        return 2.0/100.0;
    }


}
