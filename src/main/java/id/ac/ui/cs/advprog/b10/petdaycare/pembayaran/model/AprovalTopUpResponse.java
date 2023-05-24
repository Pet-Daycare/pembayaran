package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AprovalTopUpResponse {
    String message;
    TopUp detail_topup;
}
