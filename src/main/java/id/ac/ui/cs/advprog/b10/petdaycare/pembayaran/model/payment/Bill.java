package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "username")
    private String username;

    @Column(name= "customer_id")
    private Integer idCustomer;

    @Column(name= "penitipan_id")
    private Integer idPenitipan;
    private double total;
    private double customerBalance;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    private String code;
    private boolean paid;
    private boolean verified;
}
