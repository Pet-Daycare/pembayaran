package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
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
        System.out.println(dto.getIdCustomer());

        System.out.println(dto.getUsername());

        if(!isCustomerDoesNotExist(request.getUsername())) {
            throw new CustomerDoesNotExistException(request.getUsername());
        }

        Customer customerAccount =  new Customer();
        customerAccount.setUsername(request.getUsername());
        customerAccount.setCustomerId(dto.getIdCustomer());
        customerAccount.setBalance(0.0);
        customerAccount.setPaymentList(new ArrayList<>());
        customerAccount.setTopUpList(new ArrayList<>());

        customerRepository.save(customerAccount);

        return customerAccount;
    }
    @Override
    public Double addBalance(String username, double amount){
        Customer customer = findCustomer(username);
        Double newCustomerBalance = customer.getBalance() + amount;
        customer.setBalance(newCustomerBalance);
        return amount;
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
        List<TopUp>  customerLits = customer.getTopUpList();
        System.out.println(customerLits);
        return customerLits;
    }
    public boolean isCustomerDoesNotExist(String username){
        return customerRepository.findCustomersByusername(username).isEmpty();
    }

    private AuthTransactionDto verifyToken(String token) throws InterruptedException{
        String otherInstanceURL = "http://localhost:8080/api/v1/auth/verify-token/"+token;
        // Panggil service arti untuk mengecek id article (post) yang bersesuaian
        AuthTransactionDto res = restTemplate.getForObject((otherInstanceURL), AuthTransactionDto.class);
        return res;
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


