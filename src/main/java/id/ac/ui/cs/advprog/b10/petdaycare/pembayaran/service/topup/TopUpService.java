package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;

import java.util.List;

public interface TopUpService {
    TopUp createTopUpRequest(TopUpRequest request);
    TopUp getDetailTopUp(String id);
    String aprovalTopUp(String id);
    List<TopUp> getAllNotAprove();

}