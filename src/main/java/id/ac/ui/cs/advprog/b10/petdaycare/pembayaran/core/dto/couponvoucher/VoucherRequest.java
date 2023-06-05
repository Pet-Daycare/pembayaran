package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponVoucher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRequest {
    private double amount;
}