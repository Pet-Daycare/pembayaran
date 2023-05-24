package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer_pembayaran")
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name= "customer_id")
    private Integer customerId;

    @Column(name = "username")
    private String username;

    @Column(name = "balance")
    private Double balance;

    //    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "topUp_id")
    private List<TopUp> topUpList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private List<Bill> paymentList;

    public void setToken(String abc123) {
    }
}

