package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "topup_master")
public class TopUp {

    @Id
    @Column(unique=true, columnDefinition = "CHAR(32)")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name= "customer_id")
    private Integer customerId;

    private String typeMethod;

    private String timeTaken;
    private  Double nominal;
    private double accumulateNominal;
    private boolean validate;
}

