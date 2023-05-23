package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerRequest request);
    Customer findCustomer(String username);

    Customer getCustomerFrontend(String username, String token);
    Double addBalance(String username, double amount);
    List<TopUp> historyTopUp(String username);

}
