package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.builder;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.TopUpCalulate;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpTypeBrand;

public abstract class TopUpBuilder {
    protected  abstract TopUpCalulate createTopUp(TopUpTypeBrand topUpTypeBrand);

    public TopUpCalulate getTopUp(TopUpTypeBrand topUpTypeBrand){
        TopUpCalulate topUp = createTopUp(topUpTypeBrand);
        topUp.create();
        topUp.calculateSummaryAmount();
        return topUp;
    }
}
