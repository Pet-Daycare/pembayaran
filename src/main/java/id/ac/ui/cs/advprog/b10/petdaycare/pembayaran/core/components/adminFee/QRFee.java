package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminFee;

public class QRFee implements AdminFee{

    @Override
    public Double getFee(){
        return 0.7/100;
    }
}
