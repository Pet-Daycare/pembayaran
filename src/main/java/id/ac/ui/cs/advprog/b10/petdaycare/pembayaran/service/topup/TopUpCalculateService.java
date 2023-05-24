package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.TopUpCalulate;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpTypeBrand;

public interface TopUpCalculateService {
    TopUpCalulate createCalculateTopUp(TopUpTypeBrand topUpTypeBrand);
}
