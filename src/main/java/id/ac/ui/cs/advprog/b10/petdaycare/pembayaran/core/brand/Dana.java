package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;

public class Dana extends TopUpCalulate {

    TopUpFactory topUpFactory;

    public Dana(TopUpFactory topUpFactory){
        this.topUpFactory = topUpFactory;
    }

    public void create(){
        setAdminFee(topUpFactory.addFee());
        setCashback(topUpFactory.addCashback());
    }

    public void calculateSummaryAmount(){
        double summaryGetAmount = (getCashback().getCashback() - getAdminFee().getFee());
        setSummaryTopUp(summaryGetAmount);
    }
}
