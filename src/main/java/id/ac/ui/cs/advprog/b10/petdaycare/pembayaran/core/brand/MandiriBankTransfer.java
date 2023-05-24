package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;

public class MandiriBankTransfer extends TopUpCalulate {
    TopUpFactory topUpFactory;
    public MandiriBankTransfer(TopUpFactory topUpFactory){
        this.topUpFactory = topUpFactory;
    }
    public void create(){
        setAdminFee(topUpFactory.addFee());
        setCashback(topUpFactory.addCashback());
    }
    public void calculateSummaryAmount(){
        double summary = (getCashback().getCashback() - getAdminFee().getFee());
        setSummaryTopUp(summary);
    }
}
