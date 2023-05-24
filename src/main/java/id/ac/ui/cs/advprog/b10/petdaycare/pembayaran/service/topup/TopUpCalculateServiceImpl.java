package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.TopUpCalulate;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.builder.SystemBuilder;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.builder.TopUpBuilder;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpTypeBrand;

public class TopUpCalculateServiceImpl implements TopUpCalculateService {

    TopUpBuilder builder = new SystemBuilder();
    @Override
    public TopUpCalulate createCalculateTopUp(TopUpTypeBrand topUpTypeBrand){
        TopUpCalulate topUp = builder.getTopUp(topUpTypeBrand);
        return topUp;
        }
    }
