package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment;

import lombok.Getter;

@Getter
public class PaymentDto {
    private String couponId;
    private String paymentId;

    public PaymentDto(String couponId, String paymentId) {
        this.couponId = couponId;
        this.paymentId = paymentId;
    }

}

