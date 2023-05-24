package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.builder;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.factory.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpTypeBrand;

public class SystemBuilder extends TopUpBuilder{
    protected TopUpCalulate createTopUp(TopUpTypeBrand topUpTypeBrand) {
        TopUpCalulate topUp = null;
        TopUpFactory topUpFactory;

        if(topUpTypeBrand.equals(TopUpTypeBrand.GOPAY)){
            topUpFactory = new GopayFactory();
            topUp = new Gopay(topUpFactory);

        } else if(topUpTypeBrand.equals(TopUpTypeBrand.DANA)){
            topUpFactory = new DanaFactory();
            topUp = new Dana(topUpFactory);

        } else if(topUpTypeBrand.equals(TopUpTypeBrand.QRIS)){
            topUpFactory = new QrisFactory();
            topUp = new Qris(topUpFactory);

        } else if(topUpTypeBrand.equals(TopUpTypeBrand.MANDIRI_BANK_TRANSFER)){
            topUpFactory = new MandiriBankTransferFactory();
            topUp = new MandiriBankTransfer(topUpFactory);

        } else if(topUpTypeBrand.equals(TopUpTypeBrand.BCA_BANK_TRANSFER)){
            topUpFactory = new BCABankTransferFactory();
            topUp = new BCABankTransfer(topUpFactory);
        }
        return topUp;
    }

}
