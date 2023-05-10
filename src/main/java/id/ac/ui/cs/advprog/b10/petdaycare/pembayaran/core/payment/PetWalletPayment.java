package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Bill;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PetWalletPayment implements Payment {
    private double balance;

    public PetWalletPayment(Bill bill) {
        this.balance = bill.getCustomerBalance();
    }

    @Override
    public Bill pay(Bill bill) {
        double reducedBalance =  this.balance - bill.getTotal();
        if (reducedBalance < 0) {
            return bill;
        }
        else {
            this.balance = reducedBalance;
            bill.setCustomerBalance(this.balance);
            bill.setPaid(true);
            return bill;
        }
    }
}
