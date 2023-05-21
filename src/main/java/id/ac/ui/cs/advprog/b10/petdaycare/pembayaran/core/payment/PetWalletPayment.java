package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.PaymentMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PetWalletPayment implements Payment {
    private CustomerService customerService;
    private Bill bill;

    public PetWalletPayment(Bill bill, CustomerService customerService) {
        this.bill = bill;
        this.customerService = customerService;
    }

    @Override
    public Bill pay(Bill bill) {
        Customer customer = customerService.findCustomer(bill.getUsername());
        var balance = customer.getBalance();

        double reducedBalance =  balance - bill.getTotal();
        if (reducedBalance < 0) {
            return bill;
        }
        else {
            balance = reducedBalance;
            bill.setCustomerBalance(balance);
            customer.setBalance(customer.getBalance() - bill.getTotal());
            bill.setMethod(PaymentMethod.PET_WALLET);
            bill.setPaid(true);
            return bill;
        }
    }
}
