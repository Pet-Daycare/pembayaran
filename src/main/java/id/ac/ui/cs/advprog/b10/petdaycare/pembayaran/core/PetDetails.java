package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
public class PetDetails {

    private String id;

    private String petName;
    private Integer quantity;
    private Integer totalPrice;
}
