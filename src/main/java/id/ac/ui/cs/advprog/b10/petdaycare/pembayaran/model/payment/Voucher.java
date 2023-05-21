package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue
    private Integer id;

    private String code;
    private double amount;
    private boolean isRedeemed;

    public synchronized Bill redeem(Bill bill) {
        if (bill.getTotal() == this.amount) {
            this.isRedeemed = true;
            bill.setPaid(true);
            return bill;
        }
        return bill;
    }
}
