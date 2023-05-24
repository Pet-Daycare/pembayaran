package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;

public class Qris extends TopUpCalulate {
    TopUpFactory topUpFactory;

    public Qris(TopUpFactory topUpFactory){
        this.topUpFactory = topUpFactory;
    }

    public void create(){
        setCashback(topUpFactory.addCashback());
        setAdminFee(topUpFactory.addFee());
    }

    public void calculateSummaryAmount(){
        double summary = (getCashback().getCashback() - getAdminFee().getFee());
        setSummaryTopUp(summary);
    }
}
