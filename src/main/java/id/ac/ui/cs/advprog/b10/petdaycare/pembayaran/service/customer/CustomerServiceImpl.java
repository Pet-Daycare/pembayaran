package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.CustomerAlreadyExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.CustomerDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.CustomerRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;
    TopUpService topUpService;

    @Override
    public Customer createCustomer(CustomerRequest request){
        CompletableFuture<AuthTransactionDto> futureDto = CompletableFuture.supplyAsync(
                getAuthTransactionDtoSupplier(request.getToken())
        );

        AuthTransactionDto dto = futureDto.join();

        if (dto == null) {
            Customer customerAccount =  new Customer();
            customerAccount.setUsername(request.getUsername());
            customerAccount.setBalance(0.0);
            customerAccount.setPaymentList(new ArrayList<>());
            customerAccount.setTopUpList(new ArrayList<>());

            customerRepository.save(customerAccount);

            return customerAccount;
        }

        if(!isCustomerDoesNotExist(request.getUsername())) {
            throw new CustomerAlreadyExistException(request.getUsername());
        }

        Customer customerAccount =  new Customer();
        customerAccount.setCustomerId(dto.getIdCustomer());
        customerAccount.setUsername(dto.getUsername());
        customerAccount.setBalance(0.0);
        customerAccount.setPaymentList(new ArrayList<>());
        customerAccount.setTopUpList(new ArrayList<>());
        customerRepository.save(customerAccount);
        return customerAccount;
    }
    @Override
    public Double addBalance(String username, double amount){
        Customer customer = findCustomer(username);

        if(customer == null) {
            return null;
        }

        Double newCustomerBalance = customer.getBalance() + amount;
        customer.setBalance(newCustomerBalance);
        return newCustomerBalance;
    }
    @Override
    public Customer findCustomer(String username){
        if(isCustomerDoesNotExist(username)) {
            return null;
        }
        return customerRepository.findCustomersByusername(username).get();
    }

    @Override
    public Customer getCustomerFrontend(String username, String token){
        if(isCustomerDoesNotExist(username)) {
            return createCustomer(new CustomerRequest(username, token));
        }
        return customerRepository.findCustomersByusername(username).get();

    }
    @Override
    public List<TopUp> historyTopUp(String username){
        Customer customer = findCustomer(username);
        List<TopUp>  customerList;
        if (customer == null) {
            return new ArrayList<>();
        }
        customerList = customer.getTopUpList();
        return customerList;
    }

    @Override
    public void setBalance(Object any, double v) {
        Customer customer = (Customer) any;
        customer.setBalance(v);
    }

    public boolean isCustomerDoesNotExist(String username){
        return customerRepository.findCustomersByusername(username).isEmpty();
    }

    private AuthTransactionDto verifyToken(String token) throws InterruptedException{
        String otherInstanceURL = "http://localhost:8080/api/v1/auth/verify-token/"+token;

        return restTemplate.getForObject((otherInstanceURL), AuthTransactionDto.class);
    }

    private Supplier<AuthTransactionDto> getAuthTransactionDtoSupplier(String token) {
        return () -> {
            try {
                return verifyToken(token);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        };
    }

}


