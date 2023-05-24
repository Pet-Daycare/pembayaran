package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;

public class Gopay extends TopUpCalulate {
    TopUpFactory topUpFactory;

    public Gopay(TopUpFactory topUpFactory){
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
