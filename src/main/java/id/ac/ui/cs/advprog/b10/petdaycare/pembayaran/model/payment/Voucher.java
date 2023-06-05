package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
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

    public synchronized Bill redeem(Bill bill, CustomerService customerService) {
        Customer customer = customerService.findCustomer(bill.getUsername());

        if (bill.getTotal() == this.amount) {
            this.isRedeemed = true;
            customer.setBalance(customer.getBalance());
            bill.setMethod(PaymentMethod.PET_WALLET_WITH_VOUCHER);
            bill.setCustomerBalance(customer.getBalance());
            bill.setPaid(true);
            return bill;
        }
        return bill;
    }
}
