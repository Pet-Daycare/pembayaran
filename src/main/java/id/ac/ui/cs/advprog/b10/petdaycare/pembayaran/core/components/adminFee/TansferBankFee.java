package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminFee;

public class TansferBankFee implements AdminFee{

    @Override
    public Double getFee(){
        return 1.0/100;
    }
}
