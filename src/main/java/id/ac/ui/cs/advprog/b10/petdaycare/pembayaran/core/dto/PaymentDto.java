package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto;

import lombok.Getter;

@Getter
public class PaymentDto {
    private String couponId;
    private String customerId;
    private String petId;

    public PaymentDto(String couponId, String customerId, String petId) {
        this.couponId = couponId;
        this.customerId = customerId;
        this.petId = petId;
    }

}

