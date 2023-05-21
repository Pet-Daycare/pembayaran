package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.CustomerDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.CustomerRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    TopUpService topUpService;

    @Override
    public Customer createCustomer(CustomerRequest request){
        System.out.println(request.getUsername());
        if(!isCustomerDoesNotExist(request.getUsername())) {
            throw new CustomerDoesNotExistException(request.getUsername());
        }

        Customer customerAccount =  new Customer();
        customerAccount.setUsername(request.getUsername());
        customerAccount.setBalance(0.0);
        customerRepository.save(customerAccount);
        return customerAccount;
    }
    @Override
    public Double addBalance(String username, double amount){
        Customer customer = findCustomer(username);
        Double newCustomerBalance = customer.getBalance() + amount;
        customer.setBalance(newCustomerBalance);
        return 0.0;
    }
    @Override
    public Customer findCustomer(String username){
        if(isCustomerDoesNotExist(username)) {
            return null;
        }
        return customerRepository.findCustomersByusername(username).get();
    }

    @Override
    public List<TopUp> historyTopUp(String username){
        Customer customer = findCustomer(username);
        List<TopUp>  customerLits = customer.getTopUpList();
        System.out.println(customerLits);
        return customerLits;
    }
    public boolean isCustomerDoesNotExist(String username){
        return customerRepository.findCustomersByusername(username).isEmpty();
    }
}


