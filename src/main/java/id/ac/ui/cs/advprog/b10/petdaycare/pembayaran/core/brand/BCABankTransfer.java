package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.TopUpFactory;

public class BCABankTransfer extends TopUpCalulate {

    TopUpFactory topUpFactory;
    public BCABankTransfer(TopUpFactory topUpFactory){
        this.topUpFactory = topUpFactory;
    }

    public void create(){
        setAdminFee(topUpFactory.addFee());
        setCashback(topUpFactory.addCashback());
    }

    public void calculateSummaryAmount(){
        double total = 0;
        setSummaryTopUp(total);
    }
}
