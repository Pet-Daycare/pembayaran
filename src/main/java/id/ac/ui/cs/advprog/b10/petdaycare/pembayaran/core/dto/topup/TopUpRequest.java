package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopUpRequest {
    private String username;

    private String token;
    private String typeMethod;
    private  double nominal;
}
